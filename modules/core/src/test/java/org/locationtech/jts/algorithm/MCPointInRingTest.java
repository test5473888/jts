/*
 * Copyright (c) 2016 Vivid Solutions.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */
package org.locationtech.jts.algorithm;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Location;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.WKTReader;

import junit.textui.TestRunner;

/**
 * Tests PointInRing algorithms
 *
 * @version 1.7
 */
public class MCPointInRingTest extends AbstractPointInRingTest {

  private WKTReader reader = new WKTReader();

  public static void main(String args[]) {
    TestRunner.run(PointInRingTest.class);
  }

  public MCPointInRingTest(String name) { super(name); }


   protected void runPtInRing(int expectedLoc, Coordinate pt, String wkt)
      throws Exception
  {
  	 // isPointInRing is not defined for pts on boundary
  	 if (expectedLoc == Location.BOUNDARY)
  		 return;
  	 
    Geometry geom = reader.read(wkt);
    if (! (geom instanceof Polygon))
    	return;
    
    LinearRing ring = (LinearRing) ((Polygon) geom).getExteriorRing();
    boolean expected = expectedLoc == Location.INTERIOR;
    MCPointInRing pir = new MCPointInRing(ring);
    boolean result = pir.isInside(pt);
    assertEquals(expected, result);
  }

}