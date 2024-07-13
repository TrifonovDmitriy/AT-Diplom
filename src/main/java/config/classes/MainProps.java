package config.classes;

import config.interfaces.TestLoginProps;
import config.interfaces.TestEnvironmentProps;
import config.interfaces.TestWebProps;
import org.aeonbits.owner.ConfigFactory;

public class MainProps {
    public static TestEnvironmentProps environmentProps = ConfigFactory.create(TestEnvironmentProps.class);
    public static TestLoginProps loginProps = ConfigFactory.create(TestLoginProps.class);
    public static TestWebProps webProps = ConfigFactory.create(TestWebProps.class);
}
