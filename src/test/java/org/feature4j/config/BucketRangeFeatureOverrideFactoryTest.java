package org.feature4j.config;

import com.google.common.base.Optional;
import com.google.common.collect.Range;
import junit.framework.TestCase;
import org.feature4j.FeaturesContext;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

/**
 * Created by dannwebster on 6/15/15.
 */
public class BucketRangeFeatureOverrideFactoryTest extends TestCase {

  BucketRangeFeatureOverrideFactory subject = new BucketRangeFeatureOverrideFactory();

  @Test
  public void testGetRangeWithOneValueInCorrectFormatShouldReturnRange() throws Exception {
    // When
    Optional<Range<Integer>> range = subject.getRange("2");
    assertEquals(true, range.isPresent());
    assertEquals(Range.closed(2, 2), subject.getRange("2").get());
  }

  @Test
  public void testGetRangeWithTwoValuesInCorrectFormatShouldReturnRange() throws Exception {
    // When
    Optional<Range<Integer>> range = subject.getRange("2,50");
    assertEquals(true, range.isPresent());
    assertEquals(Range.closed(2, 50), range.get());
  }

  @Test
  public void testGetRangeOnWrongFormatsReturnsNoRange() throws Exception {
    assertEquals(false, subject.getRange(null).isPresent());
    assertEquals(false, subject.getRange("").isPresent());
    assertEquals(false, subject.getRange("2,X").isPresent());
    assertEquals(false, subject.getRange("X,2").isPresent());
    assertEquals(false, subject.getRange("X").isPresent());
    assertEquals(false, subject.getRange("2,3,4").isPresent());
    assertEquals(false, subject.getRange("2,3,4").isPresent());
  }

  @Test
  public void testRangeMatcherShouldCreateMatcherThatWillMatchFeatureContextRanges() throws Exception {
    // GIVEN
    FeaturesContext inContext = mock(FeaturesContext.class);
    FeaturesContext outContext = mock(FeaturesContext.class);

    when(inContext.getBucketId()).thenReturn(20);
    when(outContext.getBucketId()).thenReturn(50);


    // WHEN
    Matcher matcher = subject.createRangeMatcher(Range.closed(0, 20));

    // THEN
    assertEquals(true, matcher.matches(inContext));
    assertEquals(false, matcher.matches(outContext));


  }
}