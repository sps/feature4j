package org.feature4j;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by dannwebster on 6/15/15.
 */
public class SimpleFeaturesContextTest extends TestCase {

  @Test
  public void testEmptyContextShouldHaveBucket0() throws Exception {
    // WHEN
    FeaturesContext subject = SimpleFeaturesContext.EMPTY;

    // THEN
    assertEquals(0, subject.getBucketId());

  }
  @Test
  public void testBuilderShouldAllowCreationOfBucketId() throws Exception {
    // WHEN
    FeaturesContext subject = SimpleFeaturesContext.builder().bucketId(1).build();

    // THEN
    assertEquals(1, subject.getBucketId());

  }
}