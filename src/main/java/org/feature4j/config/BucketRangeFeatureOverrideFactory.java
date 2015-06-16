package org.feature4j.config;

/**
 * Created by dannwebster on 6/15/15.
 */
public class BucketRangeFeatureOverrideFactory {
    private static BucketRangeFeatureOverrideFactory ourInstance = new BucketRangeFeatureOverrideFactory();

    public static BucketRangeFeatureOverrideFactory getInstance() {
        return ourInstance;
    }

    private BucketRangeFeatureOverrideFactory() {
    }
}
