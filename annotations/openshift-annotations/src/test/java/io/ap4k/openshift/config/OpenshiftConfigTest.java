package io.ap4k.openshift.config;

import static org.junit.jupiter.api.Assertions.*;

class OpenshiftConfigTest {

    public void testGeneratedCode() {
        OpenshiftConfig config = OpenshiftConfig.newOpenshiftConfigBuilder()
                .withName("myapp")
                .withVersion("1.0.0")
                .withExposeRoute(true)
                .addNewLabel("key1", "val1")
                .build();


    }

}