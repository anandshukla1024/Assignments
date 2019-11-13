package flattentransformation;

import Entity.User;
import Util.Utility;
import org.apache.beam.sdk.Pipeline;

import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionList;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FlattenTransformation {

  public static void main(String[] args) throws IOException {

    Pipeline pipeline =
        Pipeline.create(PipelineOptionsFactory.fromArgs().withValidation().create());

    File file1 = new File(Utility.textFileTopicT1);
    byte[] input1 = Files.readAllBytes(file1.toPath());

    File file2 = new File(Utility.textFileTopicT2);
    byte[] input2 = Files.readAllBytes(file2.toPath());

    PCollection<String> publisherT1 =
        pipeline.apply("Read json from text file ", Create.of(new String(input1)));
    publisherT1.apply("write it to Topic T1 ", PubsubIO.writeStrings().to(Utility.pubsubTopic1));

    PCollection<String> publisherT2 =
        pipeline.apply("Read json from text file ", Create.of(new String(input2)));
    publisherT2.apply("write it to Topic T2 ", PubsubIO.writeStrings().to(Utility.pubsubTopic1));

    PCollection<String> topic1Outcome =
        pipeline.apply(
            "Read from PubSub Topic T1: ", PubsubIO.readStrings().fromTopic(Utility.pubsubTopic1));
    PCollection<String> topic2Outcome =
        pipeline.apply(
            "Read from PubSub Topic T2: ", PubsubIO.readStrings().fromTopic(Utility.pubsubTopic2));

    PCollection<User> resultFromTopic1 =
        topic1Outcome.apply(
            "Apply ParDo Transformation to convert to a common entity",
            ParDo.of(new CombineCollection()));

    PCollection<User> resultFromTopic2 =
        topic2Outcome.apply(
            "Apply ParDo Transformation to convert to a common entity",
            ParDo.of(new CombineCollection()));

    PCollectionList<User> collections = PCollectionList.of(resultFromTopic1).and(resultFromTopic2);

    PCollection<User> merge = collections.apply(Flatten.<User>pCollections());

    merge.apply(
        "Print Object Details: ",
        ParDo.of(
            new DoFn<Object, Object>() {

              @ProcessElement
              public void printObjectDetails(@Element Object input, ProcessContext context) {
                System.out.println("After merging collections" + input.toString());
              }
            }));

    pipeline.run().waitUntilFinish();
  }
}
