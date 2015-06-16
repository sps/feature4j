package org.feature4j.config;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.feature4j.FeatureOverride;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by dannwebster on 6/15/15.
 */
public class CompositeFeatureOverridesFactory implements FeatureOverridesFactory {
  private final Iterable<FeatureOverridesFactory> featureOverridesFactories;

  public static CompositeFeatureOverridesFactory fromFactories(FeatureOverridesFactory... factories) {
    return fromIterable(Arrays.asList(factories));
  }

  public static CompositeFeatureOverridesFactory fromIterable(
      Iterable<FeatureOverridesFactory> featureOverridesFactories) {
    return new CompositeFeatureOverridesFactory(featureOverridesFactories);
  }

  CompositeFeatureOverridesFactory(Iterable<FeatureOverridesFactory> featureOverridesFactories) {
    this.featureOverridesFactories =
        ImmutableList.copyOf(checkNotNull(featureOverridesFactories, "featureOverridesFactories must be non-null"));
  }
  @Override
  public Iterable<FeatureOverride> createOverrides(FeatureConfiguration featureConfiguration) {
    List<FeatureOverride> allFeatureOverrides = new ArrayList<>();
    for (FeatureOverridesFactory factory : featureOverridesFactories) {
      Iterable<FeatureOverride> featureOverrides = factory.createOverrides(featureConfiguration);
      if (featureOverrides != null){
        Iterables.addAll(allFeatureOverrides, featureOverrides);
      }
    }
    return allFeatureOverrides;
  }
}
