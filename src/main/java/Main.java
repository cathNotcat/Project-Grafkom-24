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


public class Main {
    private Window window =
            new Window(800, 800, "Mixue");

    ArrayList<Object> eskrim = new ArrayList<>();
    ArrayList<Object> mixue = new ArrayList<>();

    ArrayList<Circle> toko = new ArrayList<>();
    ArrayList<Circle> awan = new ArrayList<>();
    ArrayList<Object> kurva = new ArrayList<>();
    ArrayList<Object> bg = new ArrayList<>();

    float default_position = -0.25f;
    float limit =-0.3f;
    int count = 5;
    float direction = 0.1f;

    Vector3f tempCenterPointParent;
    Vector3f tempCenterPoint;

    Camera camera = new Camera();
    Projection projection = new Projection(window.getWidth(), window.getHeight());




    public void init() {
        window.init();
        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        glDepthMask(true);
        glDepthFunc(GL_LEQUAL);
        glDepthRange(0.0f, 1.0f);

        camera.setPosition(-0.5f,0,1f);
        camera.setRotation((float) Math.toRadians(0.0f), (float)Math.toRadians(30.0f));

        // langit
        bg.add(new Rectangle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-1.0f, 1.0f, 0),
                                new Vector3f(-1.0f, -1.0f, 0),
                                new Vector3f(1.0f, 1.0f, 0),
                                new Vector3f(1.0f, -1.0f, 0f)
                        )
                ),
                new Vector4f(0.2f, 0.5f, 0.7f, 1f),
                Arrays.asList(0,1,2,3,1)
        ));
        bg.get(0).translateObject(0.0f, 0.0f, 1f);


        // grass
        bg.add(new Rectangle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-1.0f, -0.2f, 0),
                                new Vector3f(-1.0f, -1.0f, 0),
                                new Vector3f(1.0f, -1.0f, 0),
                                new Vector3f(1.0f, -0.2f, 0.f)
                        )
                ),
                new Vector4f(0.0f, 0.7f, 0.0f, 1f),
                Arrays.asList(0,1,2,3,1)
        ));
        bg.get(1).translateObject(0.0f, 0.0f, 1f);

        // jalan
        bg.add(new Rectangle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.1f, 0.0f, 0),
                                new Vector3f(-0.5f, -1.0f, 0),
                                new Vector3f(0.5f, -1.0f, 0),
                                new Vector3f(0.1f, 0.0f, 0f)
                        )
                ),
                new Vector4f(0.3f, 0.3f, 0.3f, 1f),
                Arrays.asList(0,1,2,3,1)
        ));
        bg.get(2).translateObject(0.0f, 0.0f, 1f);


        // START TOKO + AWAN

        // Kotak tengah putih
        toko.add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(0.9f, 0.9f, 0.9f, 0.9f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).scaleObject(10.0f, 5.0f, 3.0f);
        toko.get(0).translateObject(0.0f, 0.0f, 1.0f);


        // Kotak Merah Atas
        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(0.8f, 0.0f, 0.0f, 1),
                new Vector3f(0.0f, 0.0f, 0.3f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(0).translateObject(0.0f, 0.1255f, 0.0f);
        toko.get(0).getChildObject().get(0).scaleObject(12.0f, 3.0f, 3.0f);
        toko.get(0).getChildObject().get(0).rotateObject(0.0f, 0.0f, 0.0f, 0.3f);


        // Kotak merah bawah
        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(0.8f, 0.0f, 0.0f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(1).translateObject(0.0f, -0.35f, 0.3f);
        toko.get(0).getChildObject().get(1).scaleObject(12.0f, 0.7f, 3.0f);
        toko.get(0).getChildObject().get(1).rotateObject(0.0f, 0.0f, 0.0f, 0.0f);



//      // M kiri |
        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(2).scaleObject(0.3f, 1.7f, 0.3f);
        toko.get(0).getChildObject().get(2).translateObject(-0.37f, 0.37f, 0.73f);

        // M \ kiri
        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));


        toko.get(0).getChildObject().get(3).scaleObject(0.3f, 1.1f, 0.3f);
        toko.get(0).getChildObject().get(3).translateObject(-0.14f, 0.505f, 0.73f);
        toko.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(25f), 0.0f, 0.0f, 1f);


        // M / kanan
        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));


        toko.get(0).getChildObject().get(4).scaleObject(0.3f, 1.1f, 0.3f);
        toko.get(0).getChildObject().get(4).translateObject(-0.43f, 0.24f, 0.73f);
        toko.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(-25f), 0.0f, 0.0f, 1f);


        // M | kanan
        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(5).scaleObject(0.3f, 1.7f, 0.3f);
        toko.get(0).getChildObject().get(5).translateObject(-0.26f, 0.37f, 0.73f);

        // I
        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(6).scaleObject(0.3f, 1.7f, 0.3f);
        toko.get(0).getChildObject().get(6).translateObject(-0.17f, 0.37f, 0.73f);

        // X kiri \

        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(7).scaleObject(0.3f, 2.0f, 0.3f);
        toko.get(0).getChildObject().get(7).translateObject(0.23f, 0.29f, 0.73f);
        toko.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(40f), 0.0f, 0.0f, 1f);

        // X / Kanan
        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(8).scaleObject(0.3f, 2.0f, 0.3f);
        toko.get(0).getChildObject().get(8).translateObject(-0.245f, 0.27f, 0.73f);
        toko.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(-40f), 0.0f, 0.0f, 1f);

        // U | kiri
        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(9).scaleObject(0.3f, 1.7f, 0.3f);
        toko.get(0).getChildObject().get(9).translateObject(0.13f, 0.37f, 0.73f);

        // U _ tengah
        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(10).scaleObject(0.3f, 0.7f, 0.3f);
        toko.get(0).getChildObject().get(10).translateObject(0.3f, -0.18f, 0.73f);
        toko.get(0).getChildObject().get(10).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1f);

        // U | kanan
        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(11).scaleObject(0.3f, 1.7f, 0.3f);
        toko.get(0).getChildObject().get(11).translateObject(0.22f, 0.37f, 0.73f);

        // E
        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(12).scaleObject(0.3f, 1.7f, 0.3f);
        toko.get(0).getChildObject().get(12).translateObject(0.3f, 0.37f, 0.73f);

        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(13).scaleObject(0.3f, 0.8f, 0.3f);
        toko.get(0).getChildObject().get(13).translateObject(0.44f, -0.34f, 0.73f);
        toko.get(0).getChildObject().get(13).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1f);

        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(14).scaleObject(0.3f, 0.8f, 0.3f);
        toko.get(0).getChildObject().get(14).translateObject(0.30f, -0.34f, 0.73f);
        toko.get(0).getChildObject().get(14).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1f);

        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(1f, 1f, 1f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(15).scaleObject(0.3f, 0.8f, 0.3f);
        toko.get(0).getChildObject().get(15).translateObject(0.37f, -0.34f, 0.73f);
        toko.get(0).getChildObject().get(15).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1f);

        // Belakang Pintu Kanan
        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(0f, 0f, 0f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(16).translateObject(0.05f, -0.035f, 2.9f);
        toko.get(0).getChildObject().get(16).scaleObject(1.0f, 2.5f, 0.0f);

        // Pintu Depan Kanan
        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(0.7f, 0.7f, 0.7f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(17).translateObject(0.05f, -0.035f, 2.8f);
        toko.get(0).getChildObject().get(17).scaleObject(1.0f, 2.5f, 0.3f);

        // Pintu Depan Kanan
        toko.get(0).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(0.7f, 0.7f, 0.7f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(0).getChildObject().get(18).translateObject(-0.05f, -0.035f, 2.8f);
        toko.get(0).getChildObject().get(18).scaleObject(1.0f, 2.5f, 0.0f);



        // Terop
        toko.add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(0f, 0.0f, 0.0f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "b"
        ));

        toko.get(1).translateObject(0f, 2.3f, 0.45f);
        toko.get(1).scaleObject(12.0f, 0.1f, 1.5f);
        toko.get(1).rotateObject(0.0f, 0.0f, 0.0f, 0.0f);

        // Palang Kanan
        toko.add(new Quadric(
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
                new Vector4f(0f, 0.0f, 0.0f, 1f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 1.0f, 0.1f, "t"
        ));
        toko.get(2).scaleObject(0.02f, 0.01f, 0.335f);
        toko.get(2).rotateObject((float) Math.toRadians(90f), 1.0f, 0.0f, 0.0f);
        toko.get(2).translateObject(0.45f, -0.035f, 0.7f);

        // Palang Kiri
        toko.add(new Quadric(
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
                new Vector4f(0f, 0.0f, 0.0f, 1f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 1.0f, 0.1f, "t"
        ));
        toko.get(3).scaleObject(0.02f, 0.01f, 0.335f);
        toko.get(3).rotateObject((float) Math.toRadians(90f), 1.0f, 0.0f, 0.0f);
        toko.get(3).translateObject(-0.45f, -0.035f, 0.7f);



        // AWAN
        // elipseoid
        awan.add(new Quadric(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(0.8f, 0.9f, 0.9f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"));
        awan.get(0).scaleObject(0.9f, 0.9f, 1.2f);
        awan.get(0).translateObject(-0.7f, 0.7f, 0.0f);
        awan.get(0).rotateObject(0.0f, 0.0f, 0.0f, 0.0f);


        awan.get(0).getChildObject().add(new Quadric(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(0.8f, 0.9f, 0.9f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"));
        awan.get(0).getChildObject().get(0).scaleObject(0.9f, 0.9f, 1.2f);
        awan.get(0).getChildObject().get(0).translateObject(-0.6f, 0.75f, 0.0f);
        awan.get(0).getChildObject().get(0).rotateObject(0.0f, 0.0f, 0.0f, 0.0f);

        awan.get(0).getChildObject().add(new Quadric(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(0.8f, 0.9f, 0.9f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"));
        awan.get(0).getChildObject().get(1).scaleObject(0.9f, 0.9f, 1.2f);
        awan.get(0).getChildObject().get(1).translateObject(-0.6f, 0.7f, 0.0f);

        awan.get(0).getChildObject().add(new Quadric(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(0.8f, 0.9f, 0.9f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"));
        awan.get(0).getChildObject().get(2).scaleObject(0.9f, 0.9f, 1.2f);
        awan.get(0).getChildObject().get(2).translateObject(-0.5f, 0.7f, 0.0f);


        // Awan 2
        awan.add(new Quadric(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(0.8f, 0.9f, 0.9f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"));
        awan.get(1).scaleObject(0.9f, 0.9f, 1.2f);
        awan.get(1).translateObject(0.7f, 0.7f, 0.0f);
        awan.get(1).rotateObject(0.0f, 0.0f, 0.0f, 0.0f);


        awan.get(1).getChildObject().add(new Quadric(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(0.8f, 0.9f, 0.9f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"));
        awan.get(1).getChildObject().get(0).scaleObject(0.9f, 0.9f, 1.2f);
        awan.get(1).getChildObject().get(0).translateObject(0.6f, 0.75f, 0.0f);
        awan.get(1).getChildObject().get(0).rotateObject(0.0f, 0.0f, 0.0f, 0.0f);

        awan.get(1).getChildObject().add(new Quadric(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(0.8f, 0.9f, 0.9f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"));
        awan.get(1).getChildObject().get(1).scaleObject(0.9f, 0.9f, 1.2f);
        awan.get(1).getChildObject().get(1).translateObject(0.6f, 0.7f, 0.0f);

        awan.get(1).getChildObject().add(new Quadric(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.0f, 0.0f, 0),
                                new Vector3f(-0.0f, -0.0f, 0),
                                new Vector3f(0.0f, -0.0f, 0),
                                new Vector3f(0.0f, 0.0f, 0.f)
                        )
                ),
                new Vector4f(0.8f, 0.9f, 0.9f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"));
        awan.get(1).getChildObject().get(2).scaleObject(0.9f, 0.9f, 1.2f);
        awan.get(1).getChildObject().get(2).translateObject(0.5f, 0.7f, 0.0f);



        // END TOKO + AWAN

       // START ES KRIM
        //gelas
        eskrim.add(new Quadric(
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
                new Vector4f(1f, 0.0f, 0.0f, 1),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f,
                "foc"
        ));
        eskrim.get(0).scaleObject(0.19f, 0.2f, 0.2f); // 0.5, 0.5, 0.8
        eskrim.get(0).rotateObject(90f,0.0f, 1f, 0.0f);
        eskrim.get(0).rotateObject((float) Math.toRadians(270f),0.0f, 0.0f, 1.0f); // 4.7f
        eskrim.get(0).rotateObject((float) Math.toRadians(333f),1.0f, 0.0f, 0.0f);
        eskrim.get(0).translateObject(0.6f, -0.3f, 0.0f); //0.0, -0.5, 0.0

        // es bawah
        eskrim.get(0).getChildObject().add(new Quadric(
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
        eskrim.get(0).getChildObject().get(0).scaleObject(0.29f, 0.1f, 0.3f);
        eskrim.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(10f),0.0f, 0.0f, 1.0f);
        eskrim.get(0).getChildObject().get(0).translateObject(0.6f, -0.305f, 0.0f);

        // es tengah
        eskrim.get(0).getChildObject().add(new Quadric(
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
        eskrim.get(0).getChildObject().get(1).scaleObject(0.27f, 0.1f, 0.25f); // 0.65 0.2 0.65
        eskrim.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(10f),0.0f, 0.0f, 1.0f);
        eskrim.get(0).getChildObject().get(1).translateObject(0.6f, -0.255f, 0.0f); // 0.0 0.3 0.0

        // es atas
        eskrim.get(0).getChildObject().add(new Quadric(
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
        eskrim.get(0).getChildObject().get(2).scaleObject(0.22f, 0.1f, 0.2f); // 0.5 0.18 0.5
        eskrim.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(10f),0.0f, 0.0f, 1.0f);
        eskrim.get(0).getChildObject().get(2).translateObject(0.6f, -0.205f, 0.0f); // 0.0 0.44 0.0

        // es atas bentuk cone
        eskrim.get(0).getChildObject().add(new Quadric(
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

        eskrim.get(0).getChildObject().get(3).scaleObject(0.1f, 0.1f, 0.1f); // 0.2 0.2 0.2
        eskrim.get(0).getChildObject().get(3).rotateObject(90f,0.0f, 1f, 0.0f);
        eskrim.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(270f),0.0f, 0.0f, 1.0f); // 4.7f
        eskrim.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(153f),1.0f, 0.0f, 0.0f);
        eskrim.get(0).getChildObject().get(3).translateObject(0.6f, -0.105f, 0.0f); // 0.0 0.65 0.0

//         logo
        eskrim.get(0).getChildObject().add(new Quadric(
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
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"
        ));
//        eskrim.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(-12f),1.0f, 0.0f, 0.0f);
        eskrim.get(0).getChildObject().get(4).translateObject(0.6f, -0.5f, -0.05f);

        // mulut
        eskrim.get(0).getChildObject().add(new Curve(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.2f, 0.2f, 0),
                                new Vector3f(0f, 0f, 0),
                                new Vector3f(0.2f, 0.2f, 0.f)
                        )
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1f)

        ));
        eskrim.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-17.5f),1.0f, 0.0f, 0.0f);
        eskrim.get(0).getChildObject().get(5).scaleObject(0.23f, 0.25f, 0.0f);
        eskrim.get(0).getChildObject().get(5).translateObject(0.6f, -0.58f, -0.14f);

        // mata
        eskrim.get(0).getChildObject().add(new Quadric(
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
                new Vector4f(0.0f, 0.0f, 1.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"
        ));
        eskrim.get(0).getChildObject().get(6).scaleObject(0.17f, 0.26f, 0.2f);
        eskrim.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-12f),1.0f, 0.0f, 0.0f);
        eskrim.get(0).getChildObject().get(6).translateObject(0.56f, -0.475f, -0.125f);

        // mata putih kiri
        eskrim.get(0).getChildObject().get(6).getChildObject().add(new Quadric(
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
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"
        ));
        eskrim.get(0).getChildObject().get(6).getChildObject().get(0).scaleObject(0.17f, 0.26f, 0.2f);
        eskrim.get(0).getChildObject().get(6).getChildObject().get(0).rotateObject((float) Math.toRadians(-12f),1.0f, 0.0f, 0.0f);
        eskrim.get(0).getChildObject().get(6).getChildObject().get(0).translateObject(0.585f, -0.475f, -0.125f);

        eskrim.get(0).getChildObject().add(new Quadric(
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
                new Vector4f(0.0f, 0.0f, 1.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"
        ));
        eskrim.get(0).getChildObject().get(7).scaleObject(0.17f, 0.26f, 0.2f);
        eskrim.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-12f),1.0f, 0.0f, 0.0f);
        eskrim.get(0).getChildObject().get(7).translateObject(0.64f, -0.475f, -0.125f);

        // mata putih kanan
        eskrim.get(0).getChildObject().get(7).getChildObject().add(new Quadric(
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
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f,"e"
        ));
        eskrim.get(0).getChildObject().get(7).getChildObject().get(0).scaleObject(0.17f, 0.26f, 0.2f);
        eskrim.get(0).getChildObject().get(7).getChildObject().get(0).rotateObject((float) Math.toRadians(-12f),1.0f, 0.0f, 0.0f);
        eskrim.get(0).getChildObject().get(7).getChildObject().get(0).translateObject(0.665f, -0.475f, -0.125f);

        // hidung
        eskrim.get(0).getChildObject().add(new Quadric(
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
                new Vector4f(1.0f, 0.5f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"
        ));
        eskrim.get(0).getChildObject().get(8).scaleObject(0.17f, 0.17f, 0.2f);
        eskrim.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(-12f),1.0f, 0.0f, 0.0f);
        eskrim.get(0).getChildObject().get(8).translateObject(0.6f, -0.51f, -0.135f);

        // boba 1
        eskrim.get(0).getChildObject().add(new Quadric(
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
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"
        ));
        eskrim.get(0).getChildObject().get(9).scaleObject(0.2f, 0.2f, 0.2f);
        eskrim.get(0).getChildObject().get(9).translateObject(0.68f, -0.26f, -0.1f);

        // boba 2
        eskrim.get(0).getChildObject().add(new Quadric(
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
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"
        ));
        eskrim.get(0).getChildObject().get(10).scaleObject(0.2f, 0.2f, 0.2f);
        eskrim.get(0).getChildObject().get(10).translateObject(0.53f, -0.24f, -0.09f);

        // boba 3
        eskrim.get(0).getChildObject().add(new Quadric(
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
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"
        ));
        eskrim.get(0).getChildObject().get(11).scaleObject(0.2f, 0.2f, 0.2f);
        eskrim.get(0).getChildObject().get(11).translateObject(0.53f, -0.26f, 0.1f);

        // boba 4
        eskrim.get(0).getChildObject().add(new Quadric(
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
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"
        ));
        eskrim.get(0).getChildObject().get(12).scaleObject(0.2f, 0.2f, 0.2f);
        eskrim.get(0).getChildObject().get(12).translateObject(0.56f, -0.15f, 0.001f);

        // boba 5
        eskrim.get(0).getChildObject().add(new Quadric(
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
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "e"
        ));
        eskrim.get(0).getChildObject().get(13).scaleObject(0.2f, 0.2f, 0.2f);
        eskrim.get(0).getChildObject().get(13).translateObject(0.68f, -0.215f, 0.08f); // z semakin kecil semakin nempel


//        mixue snow king

        //buat patokan rotasi ditempat
        mixue.add(new Quadric(
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
        mixue.get(0).scaleObject(1.0f, 1.0f, 1.0f);
        mixue.get(0).translateObject(-0.5f, -0.5f, 0.0f);

        //badan snowking
        mixue.get(0).getChildObject().add(new Quadric(
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
        mixue.get(0).getChildObject().get(0).scaleObject(2.0f, 2.0f, 2.0f);
        mixue.get(0).getChildObject().get(0).translateObject(-0.5f, -0.5f, 0.0f);

        //cape
        mixue.get(0).getChildObject().get(0).getChildObject().add(new Rectangle(
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

        mixue.get(0).getChildObject().get(0).getChildObject().get(0).scaleObject(0.5f, 0.5f, 1.0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(-15f), 1f, 0f, 0.0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(0).translateObject(-0.5f, -0.5f, 0.21f);


        //kerah kiri
        mixue.get(0).getChildObject().get(0).getChildObject().add(new Object(
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

        mixue.get(0).getChildObject().get(0).getChildObject().get(1).scaleObject(0.15f, 0.15f, 0.15f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(17f), 1f, 0f, 0.0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(1).translateObject(-0.615f, -0.36f, -0.15f);

        mixue.get(0).getChildObject().get(0).getChildObject().get(1).getChildObject().add(new Object(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.45f, 0.4f, 0),
                                new Vector3f(-0.55f, -0.4f, 0),
                                new Vector3f(0.55f, -0.4f, 0),
                                new Vector3f(0.45f, 0.4f, 0.f)

                        )
                ),
                new Vector4f(1f, 0f, 0f, 1f)

        ));

        mixue.get(0).getChildObject().get(0).getChildObject().get(1).getChildObject().get(0).scaleObject(0.32f, 0.115f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(1).getChildObject().get(0).rotateObject((float) Math.toRadians(-90f), 0f, 1f, 0.f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(1).getChildObject().get(0).rotateObject((float) Math.toRadians(-20f), 0f, 0f, 1f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(1).getChildObject().get(0).translateObject(-0.665f, -0.35f,0.01f);

        //kerah kanan
        mixue.get(0).getChildObject().get(0).getChildObject().add(new Object(
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

        mixue.get(0).getChildObject().get(0).getChildObject().get(2).scaleObject(0.15f, 0.15f, 0.15f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(180f), 0f, 1f, 0.f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(17f), 1f, 0f, 0.0f);//
        mixue.get(0).getChildObject().get(0).getChildObject().get(2).translateObject(-0.39f, -0.36f, -0.15f);

        mixue.get(0).getChildObject().get(0).getChildObject().get(2).getChildObject().add(new Object(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.45f, 0.4f, 0),
                                new Vector3f(-0.55f, -0.4f, 0),
                                new Vector3f(0.55f, -0.4f, 0),
                                new Vector3f(0.45f, 0.4f, 0.f)

                        )
                ),
                new Vector4f(1f, 0f, 0f, 1f)

        ));

        mixue.get(0).getChildObject().get(0).getChildObject().get(2).getChildObject().get(0).scaleObject(0.32f, 0.115f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(2).getChildObject().get(0).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0.f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(2).getChildObject().get(0).rotateObject((float) Math.toRadians(20f), 0f, 0f, 1f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(2).getChildObject().get(0).translateObject(-0.335f, -0.35f, 0.01f);
//
//        kepala
        mixue.get(0).getChildObject().get(0).getChildObject().add(new Quadric(
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
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).scaleObject(1.5f, 1.5f, 1.5f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).translateObject(-0.5f, -0.24f, 0.0f);

        //tangan kanan
        mixue.get(0).getChildObject().get(0).getChildObject().add(new Quadric(
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
        mixue.get(0).getChildObject().get(0).getChildObject().get(4).scaleObject(0.5f, 1.2f, 0.5f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(25f), 0f, 0f, 1f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(4).translateObject(-0.30f, -0.52f, 0.0f);

//        tangan kiri
        mixue.get(0).getChildObject().get(0).getChildObject().add(new Quadric(
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
        mixue.get(0).getChildObject().get(0).getChildObject().get(5).scaleObject(0.5f, 1.2f, 0.5f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-45f), 0f, 0f, 1f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(180f), 1f, 0f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(5).translateObject(-0.7f, -0.42f, 0.0f);

        //tongkat sakti
        mixue.get(0).getChildObject().get(0).getChildObject().add(new Quadric(
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
                0.1f, 1.0f, 0.1f, "t"
        ));
        mixue.get(0).getChildObject().get(0).getChildObject().get(6).scaleObject(0.01f, 0.01f, 0.3f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(90f), 1f, 0f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(6).translateObject(-0.79f, -0.45f, 0.0f);

        // es krim snow king
        //cone
        mixue.get(0).getChildObject().get(0).getChildObject().add(new Quadric(
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
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).scaleObject(0.05f, 0.05f, 0.15f); // 0.5, 0.5, 0.8
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).rotateObject(90f,0.0f, 1f, 0.0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(270f),0.0f, 0.0f, 1.0f); // 4.7f
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(333f),1.0f, 0.0f, 0.0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).translateObject(-0.79f, -0.25f, 0.0f); //0.0, -0.5, 0.0

        // es bawah
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().add(new Quadric(
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
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().get(0).scaleObject(0.08f, 0.03f, 0.08f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().get(0).rotateObject((float) Math.toRadians(-5f),0f, 0.0f, 1.0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().get(0).translateObject(-0.79f, -0.135f, 0.0f);

        // es tengah
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().add(new Quadric(
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
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().get(1).scaleObject(0.06f, 0.03f, 0.06f); // 0.65 0.2 0.65
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().get(1).rotateObject((float) Math.toRadians(-5f),0f, 0.0f, 1.0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().get(1).translateObject(-0.79f, -0.12f, 0.0f); // 0.0 0.3 0.0

        // es atas
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().add(new Quadric(
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
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().get(2).scaleObject(0.04f, 0.02f, 0.04f); // 0.5 0.18 0.5
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().get(2).rotateObject((float) Math.toRadians(-6f),0f, 0.0f, 1.0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().get(2).translateObject(-0.79f, -0.105f, 0.0f); // 0.0 0.44 0.0

        // es atas bentuk cone
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().add(new Quadric(
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

        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().get(3).scaleObject(0.015f, 0.015f, 0.015f); // 0.2 0.2 0.2
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().get(3).rotateObject(90f,0.0f, 1f, 0.0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().get(3).rotateObject((float) Math.toRadians(270f),0.0f, 0.0f, 1.0f); // 4.7f
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().get(3).rotateObject((float) Math.toRadians(153f),1.0f, 0.0f, 0.0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().get(3).rotateObject((float) Math.toRadians(-7f),0f, 0.0f, 1.0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(7).getChildObject().get(3).translateObject(-0.788f, -0.083f, 0.0f); // 0.0 0.65 0.0

        //mata
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
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
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(0).scaleObject(0.3f, 0.4f, 0.3f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(0).translateObject(-0.55f, -0.21f, -0.115f);

        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
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
                0.1f, 0.1f, 0.1f, "e"
        ));

        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(1).scaleObject(0.15f, 0.3f, 0.3f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(1).translateObject(-0.53f, -0.21f, -0.13f);


        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
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
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(2).scaleObject(0.3f, 0.4f, 0.3f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(2).translateObject(-0.45f, -0.21f, -0.119f);

        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
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
                0.1f, 0.1f, 0.1f, "e"
        ));

        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(3).scaleObject(0.15f, 0.3f, 0.3f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(3).translateObject(-0.43f, -0.21f, -0.115f);

        //hidung
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
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
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(4).scaleObject(0.03f, 0.03f, 0.1f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(4).rotateObject((float) Math.toRadians(180f), 1f, 0f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(4).translateObject(-0.5f, -0.275f, -0.21f);

        //mulut
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Curve(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.2f, 0.2f, 0),
                                new Vector3f(0f, 0f, 0),
                                new Vector3f(0.2f, 0.2f, 0.f)
                        )
                ),
                new Vector4f(0f, 0f, 1f, 1f)

        ));
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(5).scaleObject(0.25f, 0.25f, 0f);

        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(5).translateObject(-0.5f, -0.36f, -0.125f);

        //lidah
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.2f, 0.2f, 0),
                                new Vector3f(0f, 0f, 0),
                                new Vector3f(0.2f, 0.2f, 0.f)
                        )
                ),
                new Vector4f(1f, 0f, 0f, 1f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "s"
        ));
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(6).scaleObject(0.04f, 0.03f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(6).rotateObject((float) Math.toRadians(-25f), 0.0f, 0f, 1f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(6).translateObject(-0.55f, -0.313f, -0.129f);

        //mahkota
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
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
                new Vector4f(1.0f, 1.0f, 1f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "p"
        ));
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(7).scaleObject(0.2f, 0.8f, 0.8f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(7).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(7).rotateObject((float) Math.toRadians(-45f), 0f, 0f, 1f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(7).rotateObject((float) Math.toRadians(-25f), 0.0f, 1f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(7).translateObject(-0.465f, -0.125f, -0.05f);

        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
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
                new Vector4f(1.0f, 1.0f, 1f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "p"
        ));
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(8).scaleObject(0.2f, 0.8f, 0.8f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(8).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(8).rotateObject((float) Math.toRadians(-45f), 0f, 0f, 1f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(8).rotateObject((float) Math.toRadians(25f), 0.0f, 1f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(8).translateObject(-0.535f, -0.125f, -0.05f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
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
                new Vector4f(1.0f, 1.0f, 1f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "p"
        ));
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(9).scaleObject(0.2f, 1.0f, 1.0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(9).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(9).rotateObject((float) Math.toRadians(-45f), 0f, 0f, 1f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(9).translateObject(-0.5f, -0.125f, 0.05f);

        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
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
                new Vector4f(1.0f, 1.0f, 1f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "p"
        ));
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(10).scaleObject(0.2f, 0.8f, 0.8f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(10).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(10).rotateObject((float) Math.toRadians(-45f), 0f, 0f, 1f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(10).rotateObject((float) Math.toRadians(70f), 0.0f, 1f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(10).translateObject(-0.44f, -0.125f, 0.01f);

        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
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
                new Vector4f(1.0f, 1.0f, 1f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "p"
        ));
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(11).scaleObject(0.2f, 0.8f, 0.8f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(11).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(11).rotateObject((float) Math.toRadians(-45f), 0f, 0f, 1f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(11).rotateObject((float) Math.toRadians(100f), 0.0f, 1f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(11).translateObject(-0.57f, -0.125f, 0.0f);

        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
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
                new Vector4f(1.0f, 1.0f, 1f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "s"
        ));
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(12).scaleObject(0.03f, 0.03f, 0.03f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(12).rotateObject((float) Math.toRadians(25f), 0.0f, 1f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(12).translateObject(-0.435f, -0.06f, -0.0f);

        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
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
                new Vector4f(1.0f, 1.0f, 1f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "s"
        ));
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(13).scaleObject(0.03f, 0.03f, 0.03f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(13).rotateObject((float) Math.toRadians(-25f), 0.0f, 1f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(13).translateObject(-0.57f, -0.06f, -0.0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
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
                new Vector4f(1.0f, 1.0f, 1f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "s"
        ));
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(14).scaleObject(0.03f, 0.03f, 0.03f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(14).translateObject(-0.5f, -0.06f, 0.05f);

        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
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
                new Vector4f(1.0f, 1.0f, 1f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "s"
        ));
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(15).scaleObject(0.03f, 0.03f, 0.03f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(15).rotateObject((float) Math.toRadians(115f), 0.0f, 1f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(15).translateObject(-0.465f, -0.06f, -0.045f);

        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new Quadric(
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
                new Vector4f(1.0f, 1.0f, 1f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f, "s"
        ));
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(16).scaleObject(0.03f, 0.03f, 0.03f);

        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(16).rotateObject((float) Math.toRadians(75f), 0.0f, 1f, 0f);
        mixue.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(16).translateObject(-0.535f, -0.06f, -0.045f);


    }

    public void input() {


        if (window.isKeyPressed(GLFW_KEY_W)){
//            eskrim.get(0).rotateObject((float) Math.toRadians(0.1f), 1.0f, 0.0f, 0.0f);
            //eskrim.get(0).translateObject((float) Math.toRadians(0.1f), 0.0f, 0.0f);
            // TOKO + AWAN
            for (Object awan : awan){
                awan.rotateObject2((float) Math.toRadians(2f), 0.0f, 1.0f, 0.0f);
            }

            for (Object awan: awan.get(0).getChildObject()){
                awan.rotateObject((float) Math.toRadians(2f), 0.0f, 1.0f, 0.0f);
            }
            for (Object awan: awan.get(1).getChildObject()){
                awan.rotateObject((float) Math.toRadians(2f), 0.0f, 1.0f, 0.0f);
            }
            toko.get(0).getChildObject().get(16).rotateObject2((float) Math.toRadians(2f),0.0f,1f,0.0f);

        }

        //buat muter ditempat
        if (window.isKeyPressed(GLFW_KEY_R)) {
            for (Object child: mixue.get(0).getChildObject()){
                tempCenterPoint = child.updateCenterPoint();
                child.translateObject(tempCenterPoint.x * -1 , tempCenterPoint.y * -1 , tempCenterPoint.z * -1 );
                //dikali -1 supaya dia balik 0,0

                child.rotateObject((float) Math.toRadians(1f), 0.0f, 1.0f, 0.0f);

                child.translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
            }

            // awan
            for (Object child: awan.get(0).getChildObject()){
                tempCenterPoint = child.updateCenterPoint();
                child.translateObject(tempCenterPoint.x * -1 , tempCenterPoint.y * -1 , tempCenterPoint.z * -1 );
                //dikali -1 supaya dia balik 0,0

                child.rotateObject((float) Math.toRadians(1f), 0.0f, 1.0f, 0.0f);

                child.translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
            }

            // es krim muter
            tempCenterPointParent = eskrim.get(0).updateCenterPoint();
            eskrim.get(0).translateObject(tempCenterPointParent.x * -1 , tempCenterPointParent.y * -1 , tempCenterPointParent.z * -1 );
            eskrim.get(0).rotateObject((float) Math.toRadians(1f), 0.0f, 1.0f, 0.0f);
            eskrim.get(0).translateObject(tempCenterPointParent.x * 1, tempCenterPointParent.y * 1, tempCenterPointParent.z * 1);

//            for (Object child: eskrim.get(0).getChildObject()){
//                tempCenterPoint = child.updateCenterPoint();
//                child.translateObject(tempCenterPoint.x * -1 , tempCenterPoint.y * -1 , tempCenterPoint.z * -1 );
//                child.rotateObject((float) Math.toRadians(1f), 0.0f, 1.0f, 0.0f);
//                child.translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
//            }
        }

        // snow king dan es krim geser kanan kiri
        if (window.isKeyPressed(GLFW_KEY_D)) {
            if (default_position > limit *3) {
                default_position -= 0.005f;
                eskrim.get(0).translateObject((float) Math.toRadians(-direction), 0.0f, 0.0f);
                for (Object object : mixue) {
                    object.translateObject((float) Math.toRadians(direction), 0.0f, 0.0f);
//                    object.rotateObject((float) Math.toRadians(direction), 1f, 0f, 0f);
                }
            }else{
                direction = -(direction);
                default_position = -0.2f;
            }
        }

        // memperkecil
        if (window.isKeyPressed(GLFW_KEY_K)) {
            tempCenterPointParent = eskrim.get(0).updateCenterPoint();
            eskrim.get(0).translateObject(tempCenterPointParent.x * -1 , tempCenterPointParent.y * -1 , tempCenterPointParent.z * -1 );
            eskrim.get(0).scaleObject(0.99f, 0.99f, 0.99f);
            eskrim.get(0).translateObject(tempCenterPointParent.x * 1, tempCenterPointParent.y * 1, tempCenterPointParent.z * 1);

            for (Object child: mixue){
                tempCenterPoint = child.updateCenterPoint();
                child.translateObject(tempCenterPoint.x * -1 , tempCenterPoint.y * -1 , tempCenterPoint.z * -1 );
                child.scaleObject(0.995f, 0.995f, 0.995f);

                child.translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
            }

        }

        //memperbesar
        if (window.isKeyPressed(GLFW_KEY_B)) {
            tempCenterPointParent = eskrim.get(0).updateCenterPoint();
            eskrim.get(0).translateObject(tempCenterPointParent.x * -1 , tempCenterPointParent.y * -1 , tempCenterPointParent.z * -1 );
            eskrim.get(0).scaleObject(1.005f, 1.005f, 1.005f);
            eskrim.get(0).translateObject(tempCenterPointParent.x * 1, tempCenterPointParent.y * 1, tempCenterPointParent.z * 1);

            for (Object child: mixue){
                tempCenterPoint = child.updateCenterPoint();
                child.translateObject(tempCenterPoint.x * -1 , tempCenterPoint.y * -1 , tempCenterPoint.z * -1 );
                child.scaleObject(1.005f, 1.005f, 1.005f);

                child.translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
            }
        }

        // es krim gerak" kepala snowking goyang
        if (window.isKeyPressed(GLFW_KEY_S)) {

            if (default_position > limit) {
                default_position -= 0.005f;
                System.out.println(default_position);

                for(int i = 0 ; i <= 7; i++){
                    tempCenterPointParent = eskrim.get(0).updateCenterPoint();
                    eskrim.get(0).translateObject(tempCenterPointParent.x * -1 , tempCenterPointParent.y * -1 , tempCenterPointParent.z * -1 );
                    eskrim.get(0).rotateObject((float) Math.toRadians(direction), 0.0f, 0.0f, 1.0f);
                    eskrim.get(0).translateObject(tempCenterPointParent.x * 1, tempCenterPointParent.y * 1, tempCenterPointParent.z * 1);

                    tempCenterPoint = mixue.get(0).getChildObject().get(0).getChildObject().get(3).updateCenterPoint();
                    mixue.get(0).getChildObject().get(0).getChildObject().get(3).translateObject(tempCenterPoint.x * -1 , tempCenterPoint.y * -1 , tempCenterPoint.z * -1 );
                    mixue.get(0).getChildObject().get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(direction), 0f, 0f, 1f);
                    mixue.get(0).getChildObject().get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(direction), 0f, 1f, 0f);
                    mixue.get(0).getChildObject().get(0).getChildObject().get(3).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);


                }
            }
            else{
                direction = -(direction);
                default_position = -0.2f;
            }

        }
        // muter ditempat
//        if (window.isKeyPressed(GLFW_KEY_U)) {
//            eskrim.get(0).rotateObject((float) Math.toRadians(0.5f), 0.0f, 1.0f, 0.0f);
//        }
    }


    public void loop() {
//        kalo ga pake loop nnti habis buka window baru langsung ketutup (ga bisa tambahin frame)
        while(window.isOpen()){
            window.update();
            glClearColor(0.0f,0.0f,0.0f,0);
            GL.createCapabilities();
            input();

            for(Object object:bg){
                object.draw();
            }
            for(Object object:toko){
                object.draw();
            }
            for(Object object:awan){
                object.draw();
            }
            for(Object object: eskrim){
                object.draw();
            }
            for(Object object:mixue){
                object.draw();
            }


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