package com.cmstestcrunner;
import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(features="features",glue={"com.cmstestcases"})
public class TestRunner {

}
