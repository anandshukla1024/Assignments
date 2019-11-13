package flattentransformation;

import Entity.User;
import org.apache.beam.sdk.coders.DefaultCoder;
import org.apache.beam.sdk.transforms.DoFn;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class CombineCollection extends DoFn<String, User> {

  @ProcessElement
  public void convertToEntity(@Element String input, ProcessContext context) throws IOException {

    ObjectMapper mapper = new ObjectMapper();
    User user_bean = new User();
    user_bean = mapper.readValue(input, User.class);
    System.out.println("Name :" + user_bean.getName());
    System.out.println("Email :" + user_bean.getEmail());
    context.output(user_bean);
  }
}
