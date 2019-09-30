package se.accelerate.bos;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.runner.TestRunner;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 *
 *
 * @author Eric Eneroth
 * @since 2019-09-30
 */
public class bosTestCase extends TestNGCitrusTestRunner {
    @CitrusTest
    @Test
    @Parameters("testRunner")
    public void bOSTestCase(@CitrusResource @Optional TestRunner testRunner) {
        testRunner.echo("TODO: Code the test bosTestCase");

    }
}
