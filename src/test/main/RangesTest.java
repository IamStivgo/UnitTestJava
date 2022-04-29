package main;

import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Test of the Ranges class
 */
public class RangesTest {

    double precision = 0.001;

    @Test
    public void testConstructor() {
        Ranges ranges = new Ranges(1.0, 100.0);
        assertThat(ranges.sum(), closeTo(99.0, precision));

        Interval[] expected = { new Interval(1.0, 100.0) };
        assertThat (ranges, contains(expected)); // checks ranges.iterator()

        ranges.remove(new Interval(5.0, 10.0));
        assertThat(ranges.sum(), closeTo(94.0, precision));

        Interval[] expected2 = { new Interval(1.0, 5.0), new Interval(10.0, 100.0) };
        assertThat(ranges, contains(expected2));

    }

    @Test
    public void testRemove(){
        Ranges ranges = new Ranges(20, 140.0);
        Interval[] expected = { new Interval(20.0, 140.0) };
        assertThat (ranges, contains(expected));
        ranges.remove(new Interval(30.0, 30.0));
        expected = new Interval[]{new Interval(20.0, 140.0)};
        assertThat (ranges, contains(expected));
        ranges.remove(new Interval(-20.0, -10.0));
        expected = new Interval[]{new Interval(20.0, 140.0)};
        assertThat (ranges, contains(expected));
        ranges.remove(new Interval(200.0, 350.0));
        expected = new Interval[]{new Interval(20.0, 140.0)};
        assertThat (ranges, contains(expected));
        ranges.remove(new Interval(40.0, 60.0));
        expected = new Interval[]{new Interval(20.0, 40.0),new Interval(60.0, 140.0) };
        assertThat (ranges, contains(expected));
        ranges.remove(new Interval(40.0, 65.0));
        expected = new Interval[]{new Interval(20.0, 40.0),new Interval(65.0, 140.0) };
        assertThat (ranges, contains(expected));
        expected = new Interval[]{new Interval(20.0, 40.0),new Interval(65.0, 140.0) };
        assertThat (ranges, contains(expected));
        ranges.remove(new Interval(30.0, 5.0));
        expected = new Interval[]{new Interval(20.0, 40.0),new Interval(65.0, 140.0) };
        assertThat (ranges, contains(expected));
        ranges.remove(new Interval(100.0, 110.0));
        expected = new Interval[]{new Interval(20.0, 40.0),new Interval(65.0, 100.0),new Interval(110.0, 140.0) };
        assertThat (ranges, contains(expected));
    }

    @Test
    public void testSum(){
        Ranges ranges = new Ranges(200, 400.0);
        assertThat(ranges.sum(), closeTo(200.0, precision));
        ranges.remove(new Interval(200.0, 200.0));
        assertThat(ranges.sum(), closeTo(200.0, precision));
        ranges.remove(new Interval(100.0, 150.0));
        assertThat(ranges.sum(), closeTo(200.0, precision));
        ranges.remove(new Interval(200.0, 280.0));
        assertThat(ranges.sum(), closeTo(120.0, precision));
        ranges.remove(new Interval(250.0, 280.0));
        assertThat(ranges.sum(), closeTo(120.0, precision));
        ranges.remove(new Interval(300.0, 310.0));
        assertThat(ranges.sum(), closeTo(110.0, precision));
        ranges.remove(new Interval(400.0, 500.0));
        assertThat(ranges.sum(), closeTo(110.0, precision));
        ranges.remove(new Interval(350.0, 400.0));
        assertThat(ranges.sum(), closeTo(60.0, precision));
    }

}