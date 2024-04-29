import entity.Result;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class HitTest {
    private final Result result = new Result();

    @Test
    public void hitCircle() {
        result.setX(-0.2);
        result.setY(0.2);
        result.setR(1.0);
        assertTrue(result.checkHit());
    }

    @Test
    public void hitCircleLowBorder() {
        result.setX(0.);
        result.setY(1.0);
        result.setR(1.0);
        assertTrue(result.checkHit());
    }

    @Test
    public void missCircle() {
        result.setX(-1.0);
        result.setY(1.0);
        result.setR(1.0);
        assertFalse(result.checkHit());
    }

    @Test
    public void missCircleFarBorder() {
        result.setX(-1.001);
        result.setY(0.);
        result.setR(1.0);
        assertFalse(result.checkHit());
    }

    @Test
    public void missCircleLowBorder() {
        result.setX(0.);
        result.setY(1.001);
        result.setR(1.0);
        assertFalse(result.checkHit());
    }

    @Test
    public void missCircleArchBorder() {
        result.setX(-Math.sqrt(2)/2 - 0.001);
        result.setY(Math.sqrt(2)/2 + 0.001);
        result.setR(1.0);
        assertFalse(result.checkHit());
    }

    @Test
    public void hitTriangle() {
        result.setX(0.2);
        result.setY(0.2);
        result.setR(1.0);
        assertTrue(result.checkHit());
    }

    @Test
    public void hitTriangleLowBorder() {
        result.setY(0.001);
        result.setX(0.489);
        result.setR(1.0);
        assertTrue(result.checkHit());
    }

    @Test
    public void hitTriangleTopBorder() {
        result.setX(0.);
        result.setY(1.0);
        result.setR(1.0);
        assertTrue(result.checkHit());
    }

    @Test
    public void hitTriangleHBorder() {
        result.setX(0.25);
        result.setY(0.5);
        result.setR(1.0);
        assertTrue(result.checkHit());
    }

    @Test
    public void missTriangle() {
        result.setX(0.5);
        result.setY(1.0);
        result.setR(1.0);
        assertFalse(result.checkHit());
    }

    @Test
    public void missTriangleLowBorder() {
        result.setY(0.001);
        result.setX(0.5);
        result.setR(1.0);
        assertFalse(result.checkHit());
    }

    @Test
    public void missTriangleTopBorder() {
        result.setX(0.);
        result.setY(1.001);
        result.setR(1.0);
        assertFalse(result.checkHit());
    }


    @Test
    public void hitRectangle() {
        result.setX(0.2);
        result.setY(-0.2);
        result.setR(1.0);
        assertTrue(result.checkHit());
    }

    @Test
    public void hitRectangleLowBorder() {
        result.setX(0.);
        result.setY(-0.5);
        result.setR(1.0);
        assertTrue(result.checkHit());
    }

    @Test
    public void hitRectangleTopBorder() {
        result.setX(1.0);
        result.setY(0.);
        result.setR(1.0);
        assertTrue(result.checkHit());
    }

    @Test
    public void missRectangle() {
        result.setX(1.0);
        result.setY(-1.0);
        result.setR(1.0);
        assertFalse(result.checkHit());
    }

    @Test
    public void missRectangleLowBorder() {
        result.setY(- 0.5 - 0.001);
        result.setX(0.001);
        result.setR(1.0);
        assertFalse(result.checkHit());
    }

    @Test
    public void missRectangleTopBorder() {
        result.setY(0.);
        result.setX(1.0 + 0.001);
        result.setR(1.0);
        assertFalse(result.checkHit());
    }

    @Test
    public void hitEmptySector() {
        result.setX(-0.5);
        result.setY(-0.5);
        result.setR(1.0);
        assertFalse(result.checkHit());
    }

    @Test
    public void rSwitch() {
        result.setX(-1.0);
        result.setY(2.);
        result.setR(1.0);
        assertFalse(result.checkHit());
        result.setR(2.5);
        assertFalse(result.checkHit());
        result.setR(5.0);
        assertTrue(result.checkHit());
    }
}