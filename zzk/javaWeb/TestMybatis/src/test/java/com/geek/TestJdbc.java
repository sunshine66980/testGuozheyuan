package com.geek;

import com.geekaca.demo.TestBrand;
import com.geekaca.pojo.Brand;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestJdbc {
    @Test
    public void testJdbc() throws Exception {
        TestBrand brand = new TestBrand();
        Brand brand1 =  TestBrand.testSelect(1000048);
        Assert.assertNotNull(brand1);
    }

}
