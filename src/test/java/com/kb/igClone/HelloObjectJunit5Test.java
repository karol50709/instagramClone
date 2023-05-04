package com.kb.igClone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class HelloObjectJunit5Test {

    @Test
    void getText() {
        HelloObject helloObject = new HelloObject();
        Assertions.assertNotNull(helloObject);
    }
}