package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sin;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import org.lwjgl.opengl.GL11;

public class Sphere extends Circle{
    float innerRadius = 0.8f; // inner radius of the torus
    float outerRadius = 1.0f; // outer radius of the torus
    int sides = 32; // number of sides used to draw the torus
    int rings = 16; // number of rings used to draw the torus



    Float rz;
    List<Integer> index;
    int ibo;
    int stackCount = 200, sectorCount = 300;
    String choice ;

    public Sphere(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, Vector3f cp, Float rx, Float ry, Float rz, String choice) {
        super(shaderModuleDataList, vertices, color, cp, rx, ry);
        this.rz = rz;
//        drawTorus(innerRadius, outerRadius, sides, rings);
//        createBox();
//        createSphere();
//        createElipsoid();
//        createHyperboloid1();
//        createHyperboloid2();
        this.choice = choice;
        if (choice == "c") createCone();
        else if (choice == "s") createSphere();
        else if ( choice == "e") createElipsoid();
        else if ( choice == "h1") createHyperboloid1();
        else if ( choice == "t") createTube();
        else if ( choice == "b") createBox();
        else if ( choice == "ep") createEParaboloid();
        else if ( choice == "r") createRing();
        else if ( choice == "j") createJubah();
        else if ( choice == "p") createPrism();

        setupVAOVBO();
    }

    public void drawTorus(float innerRadius, float outerRadius, int sides, int rings) {
        float ringDelta = (float) (2.0f * Math.PI / rings);
        float sideDelta = (float) (2.0f * Math.PI / sides);
        float theta = 0.0f;

        for (int i = 0; i < rings; i++) {
            float phi = 0.0f;
            GL11.glBegin(GL11.GL_QUAD_STRIP);
            for (int j = 0; j <= sides; j++) {
                float cosTheta = (float) Math.cos(theta);
                float sinTheta = (float) sin(theta);
                float cosPhi = (float) Math.cos(phi);
                float sinPhi = (float) sin(phi);
                float x = (outerRadius + innerRadius * cosPhi) * cosTheta;
                float y = (outerRadius + innerRadius * cosPhi) * sinTheta;
                float z = innerRadius * sinPhi;
                GL11.glVertex3f(x, y, z);

                x = (float) ((outerRadius + innerRadius * cosPhi) * Math.cos((theta + sideDelta)));
                y = (float) ((outerRadius + innerRadius * cosPhi) * sin((theta + sideDelta)));
                z = innerRadius * sinPhi;
                GL11.glVertex3f(x, y, z);
                phi += sideDelta;
            }
            GL11.glEnd();
            theta += ringDelta;
        }
    }


    public void createBox(){
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        //TITIK 1
        temp.x = cp.get(0) - rx / 2.0f;
        temp.y = cp.get(1) + ry / 2.0f;
        temp.z = cp.get(2) - rz / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 2
        temp.x = cp.get(0) + rx / 2.0f;
        temp.y = cp.get(1) + ry / 2.0f;
        temp.z = cp.get(2) - rz / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 3
        temp.x = cp.get(0) + rx / 2.0f;
        temp.y = cp.get(1) - ry / 2.0f;
        temp.z = cp.get(2) - rz / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 4
        temp.x = cp.get(0) - rx / 2.0f;
        temp.y = cp.get(1) - ry / 2.0f;
        temp.z = cp.get(2) - rz / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 5
        temp.x = cp.get(0) - rx / 2.0f;
        temp.y = cp.get(1) + ry / 2.0f;
        temp.z = cp.get(2) + rz / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 6
        temp.x = cp.get(0) + rx / 2.0f;
        temp.y = cp.get(1) + ry / 2.0f;
        temp.z = cp.get(2) + rz / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 7
        temp.x = cp.get(0) + rx / 2.0f;
        temp.y = cp.get(1) - ry / 2.0f;
        temp.z = cp.get(2) + rz / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 8
        temp.x = cp.get(0) - rx / 2.0f;
        temp.y = cp.get(1) - ry / 2.0f;
        temp.z = cp.get(2) + rz / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();

        vertices.clear();
        //kotak yg sisi belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi depan
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(7));
        //kotak yg sisi kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi kanan
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));
        //kotak yg sisi atas
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(4));
        //kotak yg sisi bawah
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));
//        vertices.clear();
//        Vector3f temp = new Vector3f();
//        ArrayList<Vector3f> tempVertices = new ArrayList<>();
//        //titik 1 kiri atas belakang
//        temp.x = cp.get(0) - rx / 2;
//        temp.y = cp.get(1) + ry / 2;
//        temp.z = cp.get(2) - rz / 2;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//
//        //titik 2 kiri bawah belakang
//        temp.x = cp.get(0) - rx / 2;
//        temp.y = cp.get(1) - ry / 2;
//        temp.z = cp.get(2) - rz / 2;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//
//        //titik 3 kanan bawah belakang
//        temp.x = cp.get(0) + rx / 2;
//        temp.y = cp.get(1) - ry / 2;
//        temp.z = cp.get(2) - rz / 2;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//
//        //titik 4 kanan atas belakang
//        temp.x = cp.get(0) + rx / 2;
//        temp.y = cp.get(1) + ry / 2;
//        temp.z = cp.get(2) - rz / 2;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//
//        //titik 5 kiri bawah depam
//        temp.x = cp.get(0) - rx / 2;
//        temp.y = cp.get(1) + ry / 2;
//        temp.z = cp.get(2) + rz / 2;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//
//        //titik 6 kiri bawah depan
//        temp.x = cp.get(0) + rx / 2;
//        temp.y = cp.get(1) - ry / 2;
//        temp.z = cp.get(2) - rz / 2;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//
//        //titik 7 kanan bawah depan
//        temp.x = cp.get(0) + rx / 2;
//        temp.y = cp.get(1) - ry / 2;
//        temp.z = cp.get(2) + rz / 2;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//
//        //titik 8 kanan atas depan
//        temp.x = cp.get(0) + rx / 2;
//        temp.y = cp.get(1) + ry / 2;
//        temp.z = cp.get(2) + rz / 2;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//
//        vertices.clear();
//        //kotak yg sisi belakang
//        vertices.add(tempVertices.get(0));
//        vertices.add(tempVertices.get(1));
//        vertices.add(tempVertices.get(2));
//        vertices.add(tempVertices.get(3));
//        //kotak yg sisi depan
//        vertices.add(tempVertices.get(4));
//        vertices.add(tempVertices.get(5));
//        vertices.add(tempVertices.get(6));
//        vertices.add(tempVertices.get(7));
//        //kotak yg sisi kiri
//        vertices.add(tempVertices.get(0));
//        vertices.add(tempVertices.get(4));
//        vertices.add(tempVertices.get(7));
//        vertices.add(tempVertices.get(3));
//        //kotak yg sisi kanan
//        vertices.add(tempVertices.get(1));
//        vertices.add(tempVertices.get(5));
//        vertices.add(tempVertices.get(6));
//        vertices.add(tempVertices.get(2));
//        //kotak yg sisi atas
//        vertices.add(tempVertices.get(0));
//        vertices.add(tempVertices.get(1));
//        vertices.add(tempVertices.get(5));
//        vertices.add(tempVertices.get(4));
//        //kotak yg sisi bawah
//        vertices.add(tempVertices.get(3));
//        vertices.add(tempVertices.get(2));
//        vertices.add(tempVertices.get(7));
//        vertices.add(tempVertices.get(6));
    }

    public void createPrism(){
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        //TITIK 1
        temp.x = cp.get(0) - rx / 2.0f;
        temp.y = cp.get(1) + ry / 2.0f;
        temp.z = cp.get(2) - rz / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 2
        temp.x = cp.get(0) + rx / 2.0f;
        temp.y = cp.get(1) + ry / 2.0f;
        temp.z = cp.get(2) - rz / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 3
        temp.x = cp.get(0) + rx / 2.0f;
        temp.y = cp.get(1) - ry / 2.0f;
        temp.z = cp.get(2) - rz / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 4
        temp.x = cp.get(0) - rx / 2.0f;
        temp.y = cp.get(1) - ry / 2.0f;
        temp.z = cp.get(2) - rz / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 5
        temp.x = cp.get(0) - rx / 2.0f;
        temp.y = cp.get(1) + ry / 2.0f;
        temp.z = cp.get(2) + rz / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 6
        temp.x = cp.get(0) + rx / 2.0f;
        temp.y = cp.get(1) + ry / 2.0f;
        temp.z = cp.get(2) + rz / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
//        //TITIK 7
//        temp.x = cp.get(0) + rx / 2.0f;
//        temp.y = cp.get(1) - ry / 2.0f;
//        temp.z = cp.get(2) + rz / 2.0f;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//        //TITIK 8
//        temp.x = cp.get(0) - rx / 2.0f;
//        temp.y = cp.get(1) - ry / 2.0f;
//        temp.z = cp.get(2) + rz / 2.0f;
//        tempVertices.add(temp);
//        temp = new Vector3f();

        vertices.clear();
        //kotak yg sisi belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));
//        vertices.add(tempVertices.get(3));
        //kotak yg sisi depan
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
//        vertices.add(tempVertices.get(7));
        //kotak yg sisi kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(2));
        //kotak yg sisi kanan
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(1));
        //kotak yg sisi atas
//        vertices.add(tempVertices.get(0));
//        vertices.add(tempVertices.get(1));
//        vertices.add(tempVertices.get(5));
//        vertices.add(tempVertices.get(4));
        //kotak yg sisi bawah
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(2));
    }

    public void createCone() {
        this.vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList();

        for (double v = -Math.PI / 2; v <= 0; v += Math.PI / 360) {
            for (double u = -Math.PI; u <= Math.PI; u += Math.PI / 360) {
                float x = 0.5f * (float) (v * Math.cos(u));
                float y = 0.5f * (float) (v * Math.sin(u));
                float z = 0.5f * (float) (v);
                temp.add(new Vector3f(x, y, z));
            }
        }
        this.vertices = temp;
    }

    public void createSphere() {
        vertices.clear();

        ArrayList<Vector3f> temp = new ArrayList<>();
        int stackCount = 18, sectorCount = 26;
        float x,y,z,xy,nx,ny,nz;
        float sectorStep = (float)(2* Math.PI )/ sectorCount; //sector count
        float stackStep = (float)Math.PI / stackCount; // stack count
        float sectorAngle, stackAngle;

        for(int i = 0; i <= stackCount; i++){
            stackAngle = (float)Math.PI/2 - i * stackStep;
            xy = (float) (0.5f * Math.cos(stackAngle));
            z = (float) (0.5f * sin(stackAngle));
            for(int j = 0; j < sectorCount; j++){
                sectorAngle = j * sectorStep;
                x = (float) (xy * Math.cos(sectorAngle));
                y = (float) (xy * sin(sectorAngle));
                temp.add(new Vector3f(x,y,z));
            }
        }

        // !!!
        vertices = temp;

        int k1, k2;
        ArrayList<Integer> temp_indices = new ArrayList<>();
        for(int i = 0; i < stackCount; i++){
            k1 = i * (sectorCount + 1);
            k2 = k1 + sectorCount + 1;

            for(int j = 0; j < sectorCount; ++j, ++k1, ++k2){
                if(i != 0){
                    temp_indices.add(k1);
                    temp_indices.add(k2);
                    temp_indices.add(k1+1);
                }
                if(i!=(18-1)){
                    temp_indices.add(k1+1);
                    temp_indices.add(k2);
                    temp_indices.add(k2+1);
                }
            }


            this.index = temp_indices;
            ibo = glGenBuffers();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, Utils.listoInt(index), GL_STATIC_DRAW);
        }

    }

    public void createElipsoid(){
        float pi = (float)Math.PI;

        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
        float sectorAngle, StackAngle, x, y, z;

        // u stackup angle
        for (int i = 0; i <= stackCount; ++i)
        {
            StackAngle = pi / 2 - i * stackStep;
            x = rx * (float)Math.cos(StackAngle);
            y = rx * (float)Math.cos(StackAngle);
            z = rx * (float) sin(StackAngle);

            for (int j = 0; j <= sectorCount; ++j)
            {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = cp.get(0) + x * (float)Math.cos(sectorAngle);
                temp_vector.y = cp.get(1) + y * (float) sin(sectorAngle);
                temp_vector.z = cp.get(2) + z;
                vertices.add(temp_vector);
            }
        }
    }

    public void createHyperboloid1(){
        vertices.clear();
        float pi = (float)Math.PI;
        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
        float sectorAngle, StackAngle, x, y, z;

        // u stackup angle
        for (int i = 0; i <= stackCount; ++i)
        {
            StackAngle = pi / 2 - i * stackStep;
            z = rx * (float) (1.0 /Math.cos(StackAngle));
            y = ry * (float) (1.0/Math.cos(StackAngle));
            x = rz * (float) Math.tan(StackAngle);

            for (int j = 0; j <= sectorCount; ++j)
            {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.z = cp.get(0) + x * (float)Math.cos(sectorAngle);
                temp_vector.y = cp.get(1) + y * (float) sin(sectorAngle);
                temp_vector.x = cp.get(2) + z;
                vertices.add(temp_vector);
            }
        }
    }
    public void createHyperboloid2(){
        vertices.clear();
        float pi = (float)Math.PI;
        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
        float sectorAngle, StackAngle, x, y, z;

        // u stackup angle
        for (int i = 0; i <= stackCount; ++i)
        {
            StackAngle = pi / 2 - i * stackStep;
            z = rx * (float) (Math.tan(StackAngle));
            y = ry * (float) (Math.tan(StackAngle));
            x = rz * (float) (1.0/Math.cos(StackAngle));

            for (int j = 0; j <= sectorCount; ++j)
            {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.z = cp.get(0) + x * (float)Math.cos(sectorAngle);
                temp_vector.y = cp.get(1) + y * (float) sin(sectorAngle);
                temp_vector.x = cp.get(2) + z;
                vertices.add(temp_vector);
            }
        }
    }

    public void createTube(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for (double v = -Math.PI / 2; v <= Math.PI / 2; v += Math.PI / 720) {
            for (double u = -Math.PI; u <= Math.PI; u += Math.PI / 720) {
                float x = 0.5f * (float) (Math.sin(u));
                float y = 0.5f * (float) (Math.cos(u));
                float z = 0.5f * (float) v ;
                temp.add(new Vector3f(x, y, z));
            }
        }
        this.vertices = temp;
    }

    public void createEParaboloid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for (double v = 0; v <= Math.PI / 2; v += Math.PI / 60) {
            for (double u = -Math.PI; u <= Math.PI; u += Math.PI / 60) {
                float x = 0.5f * (float) (v * Math.cos(u));
                float y = 0.5f * (float) (v * Math.sin(u));
                float z = 0.5f * (float) (Math.pow(v,2));
                temp.add(new Vector3f(x, y, z));
            }
        }
        this.vertices = temp;
    }

    public void createRing(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for (double v = 0; v <= 2* Math.PI /120; v += Math.PI /120) {
            for (double u = -Math.PI; u <= Math.PI ; u += Math.PI /120) {
                float x = 0.5f * (float) (Math.cos(u));
                float y = 0.5f * (float) (Math.sin(u));
                float z = 0.5f * (float) v ;
                temp.add(new Vector3f(x, y, z));
            }
        }
        this.vertices = temp;
    }

    public void createJubah(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for (double v = -Math.PI / 2; v <= 0; v += Math.PI / 720) {
            for (double u = -Math.PI; u <= Math.PI; u += Math.PI / 720) {
                float x = 0.5f * (float) (v * Math.cos(u));
                float y = 0.5f * (float) v;
                float z = 0.5f * (float) v ;
                temp.add(new Vector3f(x, y, z));
            }
        }
        this.vertices = temp;
    }


//    public void createEllipticCone () {
//        vertices.clear();
//
//        ArrayList<Vector3f> temp = new ArrayList<>();
//
//        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
//            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
//                float x = 0.5f * (float)(v * Math.cos(u));
//                float y = 0.5f * (float)(v * Math.sin(u));
//                float z = 0.5f * (float) v;
//                temp.add(new Vector3f(x,y,z));
//            }
//        }
//        vertices = temp;
//
//        int k1, k2;
//        ArrayList<Integer> temp_indices = new ArrayList<>();
//        for(int i = 0; i < stackCount; i++){
//            k1 = i * (sectorCount + 1);
//            k2 = k1 + sectorCount + 1;
//
//            for(int j = 0; j < sectorCount; ++j, ++k1, ++k2){
//                if(i != 0){
//                    temp_indices.add(k1);
//                    temp_indices.add(k2);
//                    temp_indices.add(k1+1);
//                }
//                if(i!=(18-1)){
//                    temp_indices.add(k1+1);
//                    temp_indices.add(k2);
//                    temp_indices.add(k2+1);
//                }
//            }
//
//            this.index = temp_indices;
//            ibo = glGenBuffers();
//            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
//            glBufferData(GL_ELEMENT_ARRAY_BUFFER, Utils.listoInt(index), GL_STATIC_DRAW);
//        }
//
//    }

//    public void draw(){
//        drawSetup();
//        glLineWidth(10);
//        glPointSize(10);
//        glDrawArrays(GL_LINE_STRIP, 0, vertices.size());
//    }
}
