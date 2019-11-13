package com.mobiliya.workshop.dataflow.pipeline.steps;

import Entity.User;
import Util.Utility;
import com.mobiliya.workshop.dataflow.pipeline.options.DataPipelineOptions;
import com.mobiliya.workshop.exception.DataPipelineException;
import flattentransformation.CombineCollection;
import lombok.extern.slf4j.Slf4j;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.Flatten;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionList;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Serializable;

@Slf4j
public class DataflowPipelineBuilder implements Serializable {

  public Pipeline createDataPipeline(String[] args) throws IOException {
    //
    log.debug("create data pipeline function is started");

    final DataPipelineOptions options =
        PipelineOptionsFactory.fromArgs().withValidation().as(DataPipelineOptions.class);
    final String projectName = options.getProject();
    if (StringUtils.isEmpty(projectName)) {
      throw new DataPipelineException("Project is missing from pipeline options.");
    }

    // Create the Pipeline with the specified options
    final Pipeline pipeline = Pipeline.create(options);

    PCollection<String> publisherT1 =
        pipeline.apply("Read json from text file ", Create.of(new String(Utility.input1)));
    publisherT1.apply("write it to Topic T1 ", PubsubIO.writeStrings().to(Utility.pubsubTopic1));

    PCollection<String> publisherT2 =
        pipeline.apply("Read json from text file ", Create.of(new String(Utility.input2)));
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

    return pipeline;
  }
}
