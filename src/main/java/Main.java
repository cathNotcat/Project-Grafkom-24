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

//        rr

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