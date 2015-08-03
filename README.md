# probedock-rt-itf

> [Java EE Integration Test Framework](https://github.com/probedock/jee-itf) client for [Probe Dock RT](https://github.com/probedock/probedock-rt).

## Usage

1. Put the following dependency in your test project pom.xml

  ```xml
  <dependency>
    <groupId>io.probedock.rt.client</groupId>
    <artifactId>probedock-rt-itf</artifactId>
    <version>0.1.0</version>
  </dependenc>
  ```
  
  **Remark**: For the ITF setup, go to the [readme](https://github.com/probedock/jee-itf) of the project.

2. Extends `ProbeDockRTAbstractTestResource` in your web test project. You should replace the class which implements the
`AbstractDefaultTestResource` from ITF itself. In fact, you can simply change the `extends`.

  ```java
  import io.probedock.rt.client.itf.ProbeDockRTAbstractTestResource;
  
  @Resource
  public class TestEndPoint extends ProbeDockAbstractTestResource {
    @EJB
    private MyTestController myTestController;
  
    @Override
    public TestController getController() {
      return myTestController;
    }
  }
  ```
  
  **Remark**: If you are using the [Probe Dock ITF Client](https://github.com/probedock/probedock-itf), you should have to
  replace the `extends ProbeDockAbstractTestResource` by `extends ProbeDockRTAbstractTestResource`. It will not be other
  changes.

3. Make sure the resource is exposed as standard REST resource. If you use annotations, you can follow the next example 
present in the [demo project](https://github.com/probedock/probedock-demo-itf/blob/master/probedock-demo-itf-test-war/src/main/java/io/probedock/demo/itf)

4. Deploy your application.

5. To start the tests, you need to do `POST` request with the following content in the body. In fact, you have the full
control of the path where the resource is exposed.

  ```json
  {
    "filters": [{
      "type": "key",
      "text": "agas"
    }, {
      "type": "tag",
      "text": "feature-a"
    }],
    "seed": 123456
  }
  ```

  #### Main object
  
  | Name         | Mandatory | Description |
  | ------------ | --------- | ----------- |
  | filters[]    | No        | Define a list of filters to run specific tests. |
  | seed         | No        | Used to generate the test run order. If not sent, the order is random and the seed will appear in the logs. |
  
  #### Filter object
  
  | Name         | Mandatory | Description |
  | ------------ | --------- | ----------- |
  | type         | Yes       | The filter type: *, key, name, fingerprint, tag and ticket are valid values. |
  | text         | Yes       | Free text applied to filter type to match tests to run. |

### Requirements

* Java 6+

## Contributing

* [Fork](https://help.github.com/articles/fork-a-repo)
* Create a topic branch - `git checkout -b feature`
* Push to your branch - `git push origin feature`
* Create a [pull request](http://help.github.com/pull-requests/) from your branch

Please add a changelog entry with your name for new features and bug fixes.

## License

**probedock-rt-itf** is licensed under the [MIT License](http://opensource.org/licenses/MIT).
See [LICENSE.txt](LICENSE.txt) for the full text.
