package Util;

import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@NoArgsConstructor
public class Utility {

  public static final String textFileTopicT1 = "D:\\Big Data Assignments\\employee_topic_T1.txt";
  public static final String textFileTopicT2 = "D:\\Big Data Assignments\\employee_topic_T2.txt";
  public static final String pubsubTopic1 =
      "projects/sample-template-247208/topics/Flatten-topic-T1";
  public static final String pubsubTopic2 =
      "projects/sample-template-247208/topics/Flatten-topic-T2";
  File file1 = new File(textFileTopicT1);
  File file2 = new File(textFileTopicT2);
  public static byte[] input1,input2;
  {
    try {
      input1 = Files.readAllBytes(file1.toPath());
      input2 = Files.readAllBytes(file2.toPath());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
