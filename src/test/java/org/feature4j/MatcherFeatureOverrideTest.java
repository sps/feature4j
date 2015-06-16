package org.feature4j;

import com.google.common.base.Optional;
import junit.framework.TestCase;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dannwebster on 6/15/15.
 */
public class MatcherFeatureOverrideTest extends TestCase {

  Matcher matcher = mock(Matcher.class);

  @Test
  public void testNonMatchingFeatureShouldProvideOriginalValue() throws Exception {
    // GIVEN
    when(matcher.matches(any())).thenReturn(false);
    MatcherFeatureOverride<String> subject = new MatcherFeatureOverride<String>(matcher, "OVERRIDE");

    // WHEN
    Optional<String> opt = subject.extractFeatureValue(SimpleFeaturesContext.EMPTY);

    // THEN
    assertEquals(false, opt.isPresent());


  }

  @Test
  public void testMatchingFeatureShouldProvidOverrideValue() throws Exception {
    // GIVEN
    when(matcher.matches(any())).thenReturn(true);
    MatcherFeatureOverride<String> subject = new MatcherFeatureOverride<String>(matcher, "OVERRIDE");

    // WHEN
    Optional<String> opt = subject.extractFeatureValue(SimpleFeaturesContext.EMPTY);

    // THEN
    assertEquals(true, opt.isPresent());
    assertEquals("OVERRIDE", opt.get());

  }

}