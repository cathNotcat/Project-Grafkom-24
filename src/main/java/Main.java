import Engine.*;
import Engine.Object;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;


public class Main {
    private Window window =
            new Window(800, 800, "Hello World");

    ArrayList<Object> objects = new ArrayList<>();
    ArrayList<Object> mixue = new ArrayList<>();


    float innerRadius = 0.5f; // inner radius of the torus
    float outerRadius = 1.0f; // outer radius of the torus
    int sides = 32; // number of sides used to draw the torus
    int rings = 16; // number of rings used to draw the torus
    float posisi_Kepala = -0.25f;
    float batas_Kepala=-0.3f;
    int count = 5;
    float direction = 0.1f;

    Camera camera = new Camera();
    Projection projection = new Projection(window.getWidth(), window.getHeight());




    public void init() {
        window.init();
        GL.createCapabilities();
        camera.setPosition(-0.5f,0,1f);
        camera.setRotation((float) Math.toRadians(0.0f), (float)Math.toRadians(30.0f));

        //cone
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(1f, 0.8f, 0.0f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f,
                "c"
        ));
        objects.get(0).scaleObject(0.5f, 0.5f, 0.8f);
        objects.get(0).rotateObject(90f,0.0f, 1f, 0.0f);
        objects.get(0).rotateObject((float) Math.toRadians(270f),0.0f, 0.0f, 1.0f); // 4.7f
        objects.get(0).rotateObject((float) Math.toRadians(333f),1.0f, 0.0f, 0.0f);
        objects.get(0).translateObject(0.0f, -0.5f, 0.0f);

        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f,
                "s"
        ));
        objects.get(0).getChildObject().get(0).scaleObject(0.8f, 0.2f, 0.8f);
        objects.get(0).getChildObject().get(0).translateObject(0.0f, 0.16f, 0.0f);

        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f,
                "s"
        ));
        objects.get(0).getChildObject().get(1).scaleObject(0.65f, 0.2f, 0.65f);
        objects.get(0).getChildObject().get(1).translateObject(0.0f, 0.3f, 0.0f);

        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f,
                "s"
        ));
        objects.get(0).getChildObject().get(2).scaleObject(0.50f, 0.18f, 0.50f);
        objects.get(0).getChildObject().get(2).translateObject(0.0f, 0.44f, 0.0f);

        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f,
                "c"
        ));

        objects.get(0).getChildObject().get(3).scaleObject(0.2f, 0.2f, 0.2f);
        objects.get(0).getChildObject().get(3).rotateObject(90f,0.0f, 1f, 0.0f);
        objects.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(270f),0.0f, 0.0f, 1.0f); // 4.7f
        objects.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(153f),1.0f, 0.0f, 0.0f);
        objects.get(0).getChildObject().get(3).translateObject(0.0f, 0.65f, 0.0f);



//        mixue snow king
        //cape
        mixue.add(new Rectangle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.30f, 0.4f, 0),
                                new Vector3f(-0.55f, -0.4f, 0),
                                new Vector3f(0.55f, -0.4f, 0),
                                new Vector3f(0.30f, 0.4f, 0.f)
                        )
                ),
                new Vector4f(1f, 0f, -1.25f, 1f),
                Arrays.asList(0,1,2,1,2,3)

        ));

        mixue.get(0).scaleObject(0.5f, 0.5f, 1.0f);
        mixue.get(0).rotateObject((float) Math.toRadians(25f), 1f, 0f, 0.0f);

        mixue.get(0).translateObject(-0.5f, -0.5f, -0.15f);

        //badan snowking
        mixue.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"
        ));
        mixue.get(1).scaleObject(2.0f, 2.0f, 2.0f);
        mixue.get(1).translateObject(-0.5f, -0.5f, 0.0f);

        //kerah kiri
        mixue.add(new Object(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.25f, 0.4f, 0),
                                new Vector3f(-0.45f, -0.3f, 0),
                                new Vector3f(0.7f, -0.15f, 0)

                        )
                ),
                new Vector4f(1f, 0f, 0f, 1f)

        ));

        mixue.get(2).scaleObject(0.15f, 0.15f, 0.15f);
        mixue.get(2).translateObject(-0.615f, -0.36f, 0.0f);

        //kerah kanan
        mixue.add(new Object(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.25f, 0.4f, 0),
                                new Vector3f(-0.45f, -0.3f, 0),
                                new Vector3f(0.7f, -0.15f, 0)

                        )
                ),
                new Vector4f(1f, 0f, 0f, 1f)

        ));

        mixue.get(3).scaleObject(0.15f, 0.15f, 0.15f);
        mixue.get(3).rotateObject((float) Math.toRadians(180f), 0f, 1f, 0.0f);
        mixue.get(3).translateObject(-0.39f, -0.36f, 0.0f);


//        kepala
        mixue.get(1).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"
        ));
        mixue.get(1).getChildObject().get(0).scaleObject(1.5f, 1.5f, 1.5f);
        mixue.get(1).getChildObject().get(0).translateObject(-0.5f, -0.24f, 0.0f);

//tangan kanan
        mixue.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"
        ));
        mixue.get(4).scaleObject(0.5f, 1.2f, 1.5f);
        mixue.get(4).rotateObject((float) Math.toRadians(25f), 0f, 0f, 1f);
        mixue.get(4).translateObject(-0.30f, -0.52f, 0.0f);

//        tangan kiri
        mixue.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"
        ));
        mixue.get(5).scaleObject(0.5f, 1.2f, 1.5f);
        mixue.get(5).rotateObject((float) Math.toRadians(-45f), 0f, 0f, 1f);
        mixue.get(5).rotateObject((float) Math.toRadians(180f), 1f, 0f, 0f);

        mixue.get(5).translateObject(-0.7f, -0.42f, 0.0f);

        //tongkat sakti
        mixue.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 1.0f, 0.1f, "t"
        ));
        mixue.get(6).scaleObject(0.02f, 0.05f, 0.3f);
        mixue.get(6).rotateObject((float) Math.toRadians(90f), 1f, 0f, 0f);
        mixue.get(6).translateObject(-0.79f, -0.45f, 0.0f);

        //mata
        mixue.get(1).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(0.0f, 0.0f, 1.0f, 1f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"
        ));
        mixue.get(1).getChildObject().get(1).scaleObject(0.3f, 0.4f, 0f);
        mixue.get(1).getChildObject().get(1).translateObject(-0.55f, -0.25f, 0.0f);

        mixue.get(1).getChildObject().add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f
        ));

        mixue.get(1).getChildObject().get(2).scaleObject(0.1f, 0.3f, 0f);
        mixue.get(1).getChildObject().get(2).translateObject(-0.53f, -0.25f, 0.0f);

        mixue.get(1).getChildObject().add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(0.0f, 0.0f, 1.0f, 1f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f
        ));
        mixue.get(1).getChildObject().get(3).scaleObject(0.3f, 0.4f, 0f);
        mixue.get(1).getChildObject().get(3).translateObject(-0.45f, -0.25f, 0.0f);

        mixue.get(1).getChildObject().add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f
        ));

        mixue.get(1).getChildObject().get(4).scaleObject(0.1f, 0.3f, 0f);
        mixue.get(1).getChildObject().get(4).translateObject(-0.43f, -0.25f, 0.0f);

        //hidung
        mixue.get(1).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(1f, 0.5f, 0f, 1f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "c"
        ));
        mixue.get(1).getChildObject().get(5).scaleObject(0.03f, 0.03f, 0.1f);
        mixue.get(1).getChildObject().get(5).translateObject(-0.5f, -0.3f, 0.25f);

        //mahkota
        mixue.get(1).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(1.0f, 1.0f, 0f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "r"
        ));
        mixue.get(1).getChildObject().get(6).scaleObject(0.2f, 0.2f, 0.3f);
        mixue.get(1).getChildObject().get(6).rotateObject((float) Math.toRadians(90f), 1f, 0f, 0f);
        mixue.get(1).getChildObject().get(6).translateObject(-0.5f, -0.125f, 0.0f);


        mixue.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.5f, 0.5f, 0),
                                new Vector3f(-0.5f, -0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0),
                                new Vector3f(0.5f, 0.5f, 0.f)
                        )
                ),
                new Vector4f(1.0f, 1.0f, 0f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "j"
        ));
        mixue.get(7).scaleObject(0.5f, 0.5f, 0.5f);
        mixue.get(7).rotateObject((float) Math.toRadians(90f), 1f, 0f, 0f);
        mixue.get(7).translateObject(-0f, -0f, 0.0f);

    }

    public void input() {

        if (window.isKeyPressed(GLFW_KEY_W)){
//            objects.get(0).rotateObject((float) Math.toRadians(0.1f), 1.0f, 0.0f, 0.0f);
            objects.get(0).translateObject((float) Math.toRadians(0.1f), 0.0f, 0.0f);

            if (posisi_Kepala > batas_Kepala) {
                posisi_Kepala-= 0.005f;
                System.out.println(posisi_Kepala);
                for(int i = 0 ; i <= 4; i++){
                    Vector3f tempCenterPoint = mixue.get(1).updateCenterPoint();
                    mixue.get(1).translateObject(tempCenterPoint.x * -1 , tempCenterPoint.y * -1 , tempCenterPoint.z * -1 );
                    mixue.get(1).rotateObject((float) Math.toRadians(direction), 0f, 0f, 1f);
//                    mixue.get(1).rotateObject((float) Math.toRadians(direction), 0f, 1f, 0f);

                    mixue.get(1).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
                }

//                mixue.get(1).getChildObject().get(0).rotateObject((float) Math.toRadians(0.1f), 0f, 0f, 1f);
            }else{
                direction = -(direction);
                posisi_Kepala = -0.2f;
            }

//            for (Object child: objects.get(0).getChildObject()){
//                Vector3f tempCenterPoint = child.updateCenterPoint();
//                child.translateObject(tempCenterPoint.x * -1 ,
//                        tempCenterPoint.y * -1 ,
//                        tempCenterPoint.z * -1 );//dikali -1 supaya dia balik 0,0
//
//                child.rotateObject(0.05f, 0.0f, 0.0f, 1.0f);
//
//                child.translateObject(tempCenterPoint.x * 1,
//                        tempCenterPoint.y * 1,
//                        tempCenterPoint.z * 1);
//
//            }

//            for (Object child: objects.get(0).getChildObject().get(1).getChildObject()){
//                Vector3f tempCenterPoint = child.updateCenterPoint();
//                child.translateObject(tempCenterPoint.x * -1 ,
//                        tempCenterPoint.y * -1 ,
//                        tempCenterPoint.z * -1 );//dikali -1 supaya dia balik 0,0
//
//                child.rotateObject(0.05f, 0.0f, 0.0f, 1.0f);
//
//                child.translateObject(tempCenterPoint.x * 1,
//                        tempCenterPoint.y * 1,
//                        tempCenterPoint.z * 1);
//
//            }

        }
        if (window.isKeyPressed(GLFW_KEY_D)){
            objects.get(0).rotateObject((float) Math.toRadians(0.8f), 0.0f, 1.0f, 0.0f);


            mixue.get(7).rotateObject((float) Math.toRadians(direction), 0f, 1f, 0f);

        }
        if (window.isKeyPressed(GLFW_KEY_S)){
            objects.get(0).rotateObject((float) Math.toRadians(0.8f), 0.0f, -1.0f, 0.0f);
        }
    }


    public void loop() {
//        kalo ga pake loop nnti habis buka window baru langsung ketutup (ga bisa tambahin frame)
        while(window.isOpen()){
            window.update();
            glClearColor(0,0,0,0);
            GL.createCapabilities();
            input();

            for(Object object:objects){
                object.draw();
            }
            for(Object object:mixue){
                object.draw();
            }
            mixue.get(1).getChildObject().get(6).drawLine();
            glDisableVertexAttribArray(0);
            glfwPollEvents();
        }
    }

    public void run() {
        init();
        loop();

        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }
    public static void main(String[] args) {
        new Main().run();

    }
}