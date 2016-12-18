package org.glo.giftw.view;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;

import java.util.Stack;

/**
 * Created by alexandra on 12/5/16.
 */
public class GraphicContextDrawController
{

    private Stack<Image> states, undoStates;

    private Color drawColor;

    private double drawSize;

    public GraphicContextDrawController()
    {
        drawColor = Color.BLACK;
        drawSize = 3;
        states = new Stack<Image>();
        undoStates = new Stack<Image>();
    }

    public GraphicContextDrawController(Color initialColor)
    {
        drawColor = initialColor;
        drawSize = 3;
        states = new Stack<Image>();
        undoStates = new Stack<Image>();
    }

    public GraphicContextDrawController(double initialSize)
    {
        drawColor = Color.BLACK;
        drawSize = initialSize;
        states = new Stack<Image>();
        undoStates = new Stack<Image>();
    }

    public GraphicContextDrawController(Color initialColor, double initialSize)
    {
        drawColor = initialColor;
        drawSize = initialSize;
        states = new Stack<Image>();
        undoStates = new Stack<Image>();
    }

    public Image getCurrentDrawnState()
    {
        return states.lastElement();
    }

    public void setDrawColor(GraphicsContext gc, Color color)
    {
        gc.setStroke(color);
        gc.setFill(color);
        drawColor = color;
    }

    public void eraseAll(GraphicsContext gc)
    {
        Canvas canvas = gc.getCanvas();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void redrawPreviousState(GraphicsContext gc)
    {
        if (states.size() > 1)
        {
            undoStates.push(states.pop());
            Image lastImage = states.lastElement();
            eraseAll(gc);
            drawImage(gc, lastImage);
        }
    }

    public void redrawNextState(GraphicsContext gc)
    {
        if (!undoStates.empty())
        {
            states.push(undoStates.pop());
            Image lastImage = states.lastElement();
            eraseAll(gc);
            drawImage(gc, lastImage);
        }
    }

    public void saveLastDrawnState(GraphicsContext gc)
    {
        if (!undoStates.empty())
        {
            undoStates.removeAllElements();
        }
        Canvas canvas = gc.getCanvas();

        WritableImage currentField = new WritableImage((int) canvas.getWidth(),
                                                       (int) canvas.getHeight());
        canvas.snapshot(null, currentField);

        states.push(currentField);
    }

    public void setStrokeLineSize(GraphicsContext gc, double newSize)
    {
        gc.setLineWidth(newSize);
        drawSize = newSize;
    }

    public void setStrokeLineStyle(GraphicsContext gc, boolean isDotted)
    {
        if(isDotted) {
            gc.setLineDashes(5);
            gc.setLineDashOffset(3);
        }
        else {
            gc.setLineDashes(null);
            gc.setLineDashOffset(0);
        }
    }

    public void drawText(GraphicsContext gc, double x, double y, double size, String content)
    {
        Font font = new Font(size);
        gc.setFont(font);
        gc.strokeText(content, x, y, size);
    }

    public void drawWithPencil(GraphicsContext gc, double x, double y, double size)
    {
        gc.setFill(drawColor);
        gc.fillRect(x, y, size, size);
        setStrokeLineSize(gc, size);
    }

    public void drawOval(GraphicsContext gc, double x1, double y1, double x2, double y2)
    {
        double x, y, distX, distY;
        if (x1 > x2)
        {
            x = x2;
            distX = x1 - x2;
        }
        else
        {
            x = x1;
            distX = x2 - x1;
        }

        if (y1 > y2)
        {
            y = y2;
            distY = y1 - y2;
        }
        else
        {
            y = y1;
            distY = y2 - y1;
        }
        gc.strokeOval(x, y, distX, distY);
    }

    public void drawRectangle(GraphicsContext gc, double x1, double y1, double x2, double y2)
    {
        double x, y, distX, distY;
        if (x1 > x2)
        {
            x = x2;
            distX = x1 - x2;
        }
        else
        {
            x = x1;
            distX = x2 - x1;
        }

        if (y1 > y2)
        {
            y = y2;
            distY = y1 - y2;
        }
        else
        {
            y = y1;
            distY = y2 - y1;
        }
        gc.strokeRect(x, y, distX, distY);
    }

    public void drawLine(GraphicsContext gc, double x1, double y1, double x2, double y2)
    {
        gc.strokeLine(x1, y1, x2, y2);
    }

    public void drawArrow(GraphicsContext gc, double x1, double y1, double x2, double y2)
    {
        gc.strokeLine(x1, y1, x2, y2);

        double orientationInDegrees = Math.toDegrees(Math.atan2((x2-x1), (y2 - y1)));
        drawArrowHead(gc, x2, y2, orientationInDegrees);
    }

    public void drawArrowHead(GraphicsContext gc, double x, double y, double orientationInDegrees)
    {
        Rotate rotate = new Rotate(orientationInDegrees, x, y);
        double arrowHeadLength = 9;

        double rad = Math.toRadians(30);
        double[] arrow_x = new double[3];
        double[] arrow_y = new double[3];

        arrow_x[0] = x - arrowHeadLength*Math.cos(rad);
        arrow_x[2] = arrow_x[0];

        arrow_y[0] = y + arrowHeadLength*Math.sin(rad);
        arrow_y[2] = y - arrowHeadLength*Math.sin(rad);

        arrow_x[1] = x;
        arrow_y[1] = y;

        Point2D new0 = rotate.transform(arrow_x[0], arrow_y[0]);
        Point2D new2 = rotate.transform(arrow_x[2], arrow_y[2]);

        arrow_x[0] = new0.getX();
        arrow_y[0] = new0.getY();

        arrow_x[2] = new2.getX();
        arrow_y[2] = new2.getY();

        gc.strokePolyline(arrow_x, arrow_y, 3);
    }


    public void drawImage(GraphicsContext gc, Image image)
    {
        Canvas canvas = gc.getCanvas();
        gc.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), 0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void drawImage(GraphicsContext gc, Image image, double destW, double destH)
    {
        gc.drawImage(image, 0, 0,  image.getWidth(), image.getHeight(), 0, 0, destW, destH);
    }

    public void drawImage(GraphicsContext gc, Image image, double x, double y, double destW, double destH)
    {
        gc.drawImage(image, 0, 0,  image.getWidth(), image.getHeight(), x, y, destW, destH);
    }
}
