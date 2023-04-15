package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_LINE_STRIP;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glPointSize;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Curve extends Object{
    private ArrayList<Vector3f> v = new ArrayList<>();
    private ArrayList<Vector3f> curveVertices
            = new ArrayList<>();
    public Curve(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        this.vertices = vertices;
        updateCurve(this.vertices);
        setupVAOVBOCurve();
    }

    public List<Vector3f> getVertices() {
        return vertices;
    }

    public void setCenter(float x, float y) {

    }
    public void setupVAOVBOCurve(){
        vao = glGenVertexArrays();
        vbo = glGenBuffers();

        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(curveVertices), GL_STATIC_DRAW);
    }

    public void draw(){
//        Object line = new Object(
//                Arrays.asList(
//                        //shaderFile lokasi menyesuaikan objectnya
//                        new ShaderModuleData
//                                ("resources/shaders/scene.vert"
//                                        , GL_VERTEX_SHADER),
//                        new ShaderModuleData
//                                ("resources/shaders/scene.frag"
//                                        , GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(points),
//                new Vector4f(0.0f,0.0f,0.0f,0.0f)
//        );
//        line.drawLine();
//        if(vertices.size()<3) {
//            if(vertices.size()==2){
//                for (Vector3f vertice: vertices){
//                    curveVertices.add(vertice);
//                }
//            }
//            return;
//        }
        setupVAOVBOCurve();
        drawSetup();
        glLineWidth(5);
        glPointSize(5);
        glDrawArrays(GL_LINE_STRIP, 0, curveVertices.size());
    }
    public void updateCurve(List<Vector3f> points){
        if(vertices.size() < 3) {
            return;
        }
        curveVertices.clear();
        curveVertices.add(vertices.get(0));

        for (double i = 0; i <= 1; i += 0.01) {
            curveVertices.add(new Vector3f(Bezier((float) i, points)));
        }
        curveVertices.add(vertices.get(vertices.size()-1));
    }

    private static int binomialCoefficient(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        } else {
            return binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k);
        }
    }
    private static double Koefisien(int n, int i, double t) {
        return binomialCoefficient(n, i) * Math.pow(t, i) * Math.pow(1 - t, n - i);
    }

    public static Vector3f Bezier(float f, List<Vector3f> points) {
        int n = points.size() - 1;
        float x = 0, y = 0;

        for (int i = 0; i <= n; i++) {
            double coefficient = Koefisien(n, i, f);
            x += coefficient * points.get(i).x;
            y += coefficient * points.get(i).y;
        }

        return new Vector3f(x, y, 0.0f);
    }
}
