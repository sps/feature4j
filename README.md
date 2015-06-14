Feature4J
----------

Inspired by https://github.com/etsy/feature

Quick Start:

    // application scope
    bundleProvider = featureBundleProviderFactory.create()
    // request / session scope
    features = featureProvider.getFeatures(featuresContext);

    // default is false if flag is not present
    if (features.enabled("my.feature.enabled")) {
        
        // default to empty strings 
        String configParam = features.string("my.feature.config");
        
        // provide a default value:
        Float val = features.get("my.feature.float", Float.class, 1.0F);   
        
        // use of optionals: 
        Optional<Long> value = features.get("my.feature.long", Long.class);
    }


