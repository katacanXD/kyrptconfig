package net.kyrptonaught.kyrptconfig.config;

@Deprecated
public class ConfigWDefaults implements AbstractConfigFile {
    public transient AbstractConfigFile DEFAULTS;

    public AbstractConfigFile getDefaults() {
        return DEFAULTS;
    }

    public void afterLoad() {

    }

    public void beforeSave() {

    }
}
