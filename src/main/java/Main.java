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
            new Window(800, 800, "Hello World");

    ArrayList<Object> eskrim = new ArrayList<>();
    ArrayList<Object> mixue = new ArrayList<>();

    ArrayList<Circle> toko = new ArrayList<>();
    ArrayList<Circle> awan = new ArrayList<>();


    float innerRadius = 0.5f; // inner radius of the torus
    float outerRadius = 1.0f; // outer radius of the torus
    int sides = 32; // number of sides used to draw the torus
    int rings = 16; // number of rings used to draw the torus
    float default_position = -0.25f;
    float limit =-0.3f;
    int count = 5;
    float direction = 0.1f;

    Camera camera = new Camera();
    Projection projection = new Projection(window.getWidth(), window.getHeight());




    public void init() {
        window.init();
        GL.createCapabilities();
        camera.setPosition(-0.5f,0,1f);
        camera.setRotation((float) Math.toRadians(0.0f), (float)Math.toRadians(30.0f));

        // START TOKO + AWAN

        // Kotak tengah putih
        toko.add(new Sphere(
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
        toko.get(0).translateObject(0.0f, 0.0f, 0.0f);


        // Kotak Merah Atas
        toko.get(0).getChildObject().add(new Sphere(
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

        toko.get(0).getChildObject().get(0).translateObject(0.0f, 0.1255f, 0.0f);
        toko.get(0).getChildObject().get(0).scaleObject(12.0f, 3.0f, 3.0f);
        toko.get(0).getChildObject().get(0).rotateObject(0.0f, 0.0f, 0.0f, 0.0f);


        // Kotak merah bawah
        toko.get(0).getChildObject().add(new Sphere(
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

        toko.get(0).getChildObject().get(1).translateObject(0.0f, -0.35f, 0.0f);
        toko.get(0).getChildObject().get(1).scaleObject(12.0f, 0.7f, 3.0f);
        toko.get(0).getChildObject().get(1).rotateObject(0.0f, 0.0f, 0.0f, 0.0f);


        // Belakang Pintu Kanan
        toko.get(0).getChildObject().add(new Sphere(
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

        toko.get(0).getChildObject().get(2).translateObject(0.05f, -0.035f, 0.0f);
        toko.get(0).getChildObject().get(2).scaleObject(1.0f, 2.5f, 0.0f);

        // Pintu Depan Kanan
        toko.get(0).getChildObject().add(new Sphere(
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

        toko.get(0).getChildObject().get(3).translateObject(0.05f, -0.035f, 0.0f);
        toko.get(0).getChildObject().get(3).scaleObject(1.0f, 2.5f, 0.0f);

        // Pintu Kiri
        toko.get(0).getChildObject().add(new Sphere(
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

        toko.get(0).getChildObject().get(4).scaleObject(1.0f, 2.5f, 0.0f);
        toko.get(0).getChildObject().get(4).translateObject(-0.05f, -0.087f, 0.0f);

//      // M kiri |
        toko.get(0).getChildObject().add(new Sphere(
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
        toko.get(0).getChildObject().get(5).translateObject(-0.37f, 0.37f, 0.0f);

        // M \ kiri
        toko.get(0).getChildObject().add(new Sphere(
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


        toko.get(0).getChildObject().get(6).scaleObject(0.3f, 1.1f, 0.3f);
        toko.get(0).getChildObject().get(6).translateObject(-0.14f, 0.505f, 0.0f);
        toko.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(25f), 0.0f, 0.0f, 1f);


        // M / kanan
        toko.get(0).getChildObject().add(new Sphere(
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


        toko.get(0).getChildObject().get(7).scaleObject(0.3f, 1.1f, 0.3f);
        toko.get(0).getChildObject().get(7).translateObject(-0.43f, 0.24f, 0.0f);
        toko.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-25f), 0.0f, 0.0f, 1f);


        // M | kanan
        toko.get(0).getChildObject().add(new Sphere(
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

        toko.get(0).getChildObject().get(8).scaleObject(0.3f, 1.7f, 0.3f);
        toko.get(0).getChildObject().get(8).translateObject(-0.26f, 0.37f, 0.0f);

        // I
        toko.get(0).getChildObject().add(new Sphere(
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
        toko.get(0).getChildObject().get(9).translateObject(-0.17f, 0.37f, 0.0f);

        // X kiri \

        toko.get(0).getChildObject().add(new Sphere(
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

        toko.get(0).getChildObject().get(10).scaleObject(0.3f, 2.0f, 0.3f);
        toko.get(0).getChildObject().get(10).translateObject(0.23f, 0.29f, 0.0f);
        toko.get(0).getChildObject().get(10).rotateObject((float) Math.toRadians(40f), 0.0f, 0.0f, 1f);

        // X / Kanan
        toko.get(0).getChildObject().add(new Sphere(
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

        toko.get(0).getChildObject().get(11).scaleObject(0.3f, 2.0f, 0.3f);
        toko.get(0).getChildObject().get(11).translateObject(-0.245f, 0.27f, 0.0f);
        toko.get(0).getChildObject().get(11).rotateObject((float) Math.toRadians(-40f), 0.0f, 0.0f, 1f);

        // U | kiri
        toko.get(0).getChildObject().add(new Sphere(
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
        toko.get(0).getChildObject().get(12).translateObject(0.13f, 0.37f, 0.0f);

        // U _ tengah
        toko.get(0).getChildObject().add(new Sphere(
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

        toko.get(0).getChildObject().get(13).scaleObject(0.3f, 0.7f, 0.3f);
        toko.get(0).getChildObject().get(13).translateObject(0.3f, -0.18f, 0.0f);
        toko.get(0).getChildObject().get(13).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1f);

        // U | kanan
        toko.get(0).getChildObject().add(new Sphere(
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

        toko.get(0).getChildObject().get(14).scaleObject(0.3f, 1.7f, 0.3f);
        toko.get(0).getChildObject().get(14).translateObject(0.22f, 0.37f, 0.0f);

        // E
        toko.get(0).getChildObject().add(new Sphere(
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

        toko.get(0).getChildObject().get(15).scaleObject(0.3f, 1.7f, 0.3f);
        toko.get(0).getChildObject().get(15).translateObject(0.3f, 0.37f, 0.0f);

        toko.get(0).getChildObject().add(new Sphere(
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

        toko.get(0).getChildObject().get(16).scaleObject(0.3f, 0.8f, 0.3f);
        toko.get(0).getChildObject().get(16).translateObject(0.44f, -0.34f, 0.0f);
        toko.get(0).getChildObject().get(16).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1f);

        toko.get(0).getChildObject().add(new Sphere(
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

        toko.get(0).getChildObject().get(17).scaleObject(0.3f, 0.8f, 0.3f);
        toko.get(0).getChildObject().get(17).translateObject(0.30f, -0.34f, 0.0f);
        toko.get(0).getChildObject().get(17).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1f);

        toko.get(0).getChildObject().add(new Sphere(
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

        toko.get(0).getChildObject().get(18).scaleObject(0.3f, 0.8f, 0.3f);
        toko.get(0).getChildObject().get(18).translateObject(0.37f, -0.34f, 0.0f);
        toko.get(0).getChildObject().get(18).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1f);


        // AWAN
        // elipseoid
        awan.add(new Sphere(Arrays.asList(
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


        awan.get(0).getChildObject().add(new Sphere(Arrays.asList(
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

        awan.get(0).getChildObject().add(new Sphere(Arrays.asList(
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

        awan.get(0).getChildObject().add(new Sphere(Arrays.asList(
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

        // END TOKO + AWAN

        // START ES KRIM
        //cone
        eskrim.add(new Sphere(
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
        eskrim.get(0).scaleObject(0.2f, 0.2f, 0.3f); // 0.5, 0.5, 0.8
        eskrim.get(0).rotateObject(90f,0.0f, 1f, 0.0f);
        eskrim.get(0).rotateObject((float) Math.toRadians(270f),0.0f, 0.0f, 1.0f); // 4.7f
        eskrim.get(0).rotateObject((float) Math.toRadians(333f),1.0f, 0.0f, 0.0f);
        eskrim.get(0).translateObject(0.6f, -0.55f, 0.0f); //0.0, -0.5, 0.0

        // es bawah
        eskrim.get(0).getChildObject().add(new Sphere(
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
        eskrim.get(0).getChildObject().get(0).scaleObject(0.33f, 0.1f, 0.3f);
        eskrim.get(0).getChildObject().get(0).translateObject(0.6f, -0.3f, 0.0f);

        // es tengah
        eskrim.get(0).getChildObject().add(new Sphere(
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
        eskrim.get(0).getChildObject().get(1).translateObject(0.6f, -0.25f, 0.0f); // 0.0 0.3 0.0

        // es atas
        eskrim.get(0).getChildObject().add(new Sphere(
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
        eskrim.get(0).getChildObject().get(2).translateObject(0.6f, -0.2f, 0.0f); // 0.0 0.44 0.0

        // es atas bentuk cone
        eskrim.get(0).getChildObject().add(new Sphere(
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
        eskrim.get(0).getChildObject().get(3).translateObject(0.6f, -0.1f, 0.0f); // 0.0 0.65 0.0

        // tabung dibawah
        eskrim.get(0).getChildObject().add(new Sphere(
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
                new Vector4f(1.0f, 0.0f, 0.0f, 1f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 1.0f, 0.1f, "t"
        ));
        eskrim.get(0).getChildObject().get(4).scaleObject(0.3f, 0.3f, 0.1f);
        eskrim.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(90f), 1.0f, 0.0f, 0.0f);
        eskrim.get(0).getChildObject().get(4).translateObject(0.6f, -0.6f, 0.0f);



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
        mixue.get(6).scaleObject(0.01f, 0.01f, 0.3f);
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
        mixue.get(1).getChildObject().get(6).scaleObject(0.1f, 0.1f, 0.1f);
        mixue.get(1).getChildObject().get(6).rotateObject((float) Math.toRadians(90f), 1f, 0f, 0f);
        mixue.get(1).getChildObject().get(6).translateObject(-0.5f, -0.09f, 0.0f);

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
                0.1f, 0.1f, 0.1f, "p"
        ));
        mixue.get(1).getChildObject().get(7).scaleObject(0.1f, 1.0f, 1.0f);
        mixue.get(1).getChildObject().get(7).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
        mixue.get(1).getChildObject().get(7).rotateObject((float) Math.toRadians(-45f), 0f, 0f, 1f);
        mixue.get(1).getChildObject().get(7).rotateObject((float) Math.toRadians(25f), 0.0f, 1f, 0f);
        mixue.get(1).getChildObject().get(7).translateObject(-0.465f, -0.125f, 0.0f);

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
                0.1f, 0.1f, 0.1f, "p"
        ));
        mixue.get(1).getChildObject().get(8).scaleObject(0.1f, 1.0f, 1.0f);
        mixue.get(1).getChildObject().get(8).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
        mixue.get(1).getChildObject().get(8).rotateObject((float) Math.toRadians(-45f), 0f, 0f, 1f);
        mixue.get(1).getChildObject().get(8).rotateObject((float) Math.toRadians(-25f), 0.0f, 1f, 0f);
        mixue.get(1).getChildObject().get(8).translateObject(-0.535f, -0.125f, 0.0f);
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
                0.1f, 0.1f, 0.1f, "p"
        ));
        mixue.get(1).getChildObject().get(9).scaleObject(0.3f, 1.0f, 1.0f);
        mixue.get(1).getChildObject().get(9).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
        mixue.get(1).getChildObject().get(9).rotateObject((float) Math.toRadians(-45f), 0f, 0f, 1f);
        mixue.get(1).getChildObject().get(9).translateObject(-0.5f, -0.125f, 0.2f);

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
                0.1f, 0.1f, 0.1f, "p"
        ));
        mixue.get(1).getChildObject().get(10).scaleObject(0.1f, 1.0f, 1.0f);
        mixue.get(1).getChildObject().get(10).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
        mixue.get(1).getChildObject().get(10).rotateObject((float) Math.toRadians(-45f), 0f, 0f, 1f);
        mixue.get(1).getChildObject().get(10).rotateObject((float) Math.toRadians(115f), 0.0f, 1f, 0f);
        mixue.get(1).getChildObject().get(10).translateObject(-0.43f, -0.125f, 0.0f);

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
                0.1f, 0.1f, 0.1f, "p"
        ));
        mixue.get(1).getChildObject().get(11).scaleObject(0.1f, 1.0f, 1.0f);
        mixue.get(1).getChildObject().get(11).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
        mixue.get(1).getChildObject().get(11).rotateObject((float) Math.toRadians(-45f), 0f, 0f, 1f);
        mixue.get(1).getChildObject().get(11).rotateObject((float) Math.toRadians(75f), 0.0f, 1f, 0f);
        mixue.get(1).getChildObject().get(11).translateObject(-0.575f, -0.125f, 0.0f);

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
                0.1f, 0.1f, 0.1f, "s"
        ));
        mixue.get(1).getChildObject().get(12).scaleObject(0.03f, 0.03f, 0.03f);
        mixue.get(1).getChildObject().get(12).rotateObject((float) Math.toRadians(25f), 0.0f, 1f, 0f);
        mixue.get(1).getChildObject().get(12).translateObject(-0.465f, -0.05f, 0.0f);

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
                0.1f, 0.1f, 0.1f, "s"
        ));
        mixue.get(1).getChildObject().get(13).scaleObject(0.03f, 0.03f, 0.03f);
        mixue.get(1).getChildObject().get(13).rotateObject((float) Math.toRadians(-25f), 0.0f, 1f, 0f);
        mixue.get(1).getChildObject().get(13).translateObject(-0.535f, -0.05f, 0.0f);
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
                0.1f, 0.1f, 0.1f, "s"
        ));
        mixue.get(1).getChildObject().get(14).scaleObject(0.03f, 0.03f, 0.03f);
        mixue.get(1).getChildObject().get(14).translateObject(-0.5f, -0.05f, 0.1f);

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
                0.1f, 0.1f, 0.1f, "s"
        ));
        mixue.get(1).getChildObject().get(15).scaleObject(0.03f, 0.03f, 0.03f);
        mixue.get(1).getChildObject().get(15).rotateObject((float) Math.toRadians(115f), 0.0f, 1f, 0f);
        mixue.get(1).getChildObject().get(15).translateObject(-0.43f, -0.05f, 0.0f);

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
                0.1f, 0.1f, 0.1f, "s"
        ));
        mixue.get(1).getChildObject().get(16).scaleObject(0.03f, 0.03f, 0.03f);

        mixue.get(1).getChildObject().get(16).rotateObject((float) Math.toRadians(75f), 0.0f, 1f, 0f);
        mixue.get(1).getChildObject().get(16).translateObject(-0.575f, -0.05f, 0.0f);



//        mixue.get(7).translateObject(-0f, -0f, 0.0f);
//        mixue.add(new Sphere(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(
//                        List.of(
//                                new Vector3f(-0.5f, 0.5f, 0),
//                                new Vector3f(-0.5f, -0.5f, 0),
//                                new Vector3f(0.5f, -0.5f, 0),
//                                new Vector3f(0.5f, 0.5f, 0.f)
//                        )
//                ),
//                new Vector4f(1.0f, 1.0f, 0f, 0.0f),
//                new Vector3f(0.0f, 0.0f, 0.0f),
//                0.1f, 0.1f, 0.1f, "j"
//        ));
//        mixue.get(7).scaleObject(0.5f, 0.5f, 0.5f);
//        mixue.get(7).rotateObject((float) Math.toRadians(90f), 1f, 0f, 0f);
//        mixue.get(7).translateObject(-0f, -0f, 0.0f);

    }

    public void input() {

        if (window.isKeyPressed(GLFW_KEY_W)){
//            eskrim.get(0).rotateObject((float) Math.toRadians(0.1f), 1.0f, 0.0f, 0.0f);
            //eskrim.get(0).translateObject((float) Math.toRadians(0.1f), 0.0f, 0.0f);
            // TOKO + AWAN
            //  toko.get(0).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(0).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(1).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(2).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(3).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(4).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(5).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(6).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(7).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(8).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(9).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(10).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(11).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(12).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(13).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(14).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(15).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(16).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  toko.get(0).getChildObject().get(17).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  awan.get(0).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  awan.get(0).getChildObject().get(0).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  awan.get(0).getChildObject().get(1).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            //  awan.get(0).getChildObject().get(2).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            toko.get(0).getChildObject().get(3).rotateObject2((float) Math.toRadians(5f), 0.0f, 1.0f, 0.0f);
            // TOKO + AWAN

            if (default_position > limit) {
                default_position -= 0.005f;
                System.out.println(default_position);
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
                default_position = -0.2f;
            }
            // es krim muter
            for (Object child: eskrim.get(0).getChildObject()){
                Vector3f tempCenterPoint = child.updateCenterPoint();
                child.translateObject(tempCenterPoint.x * -1 , tempCenterPoint.y * -1 , tempCenterPoint.z * -1 );
                child.rotateObject((float) Math.toRadians(1f), 0.0f, 1.0f, 0.0f);
                child.translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
            }
        }
        if (window.isKeyPressed(GLFW_KEY_D)){
            mixue.get(1).getChildObject().get(7).rotateObject((float) Math.toRadians(direction), 0f, 1f, 0f);
            mixue.get(1).getChildObject().get(8).rotateObject((float) Math.toRadians(direction), 0f, 1f, 0f);

        }

        // es krim gerak (L kanan, J kiri)
        if (window.isKeyPressed(GLFW_KEY_L)) {

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
//
        }
        if (window.isKeyPressed(GLFW_KEY_J)) {
            eskrim.get(0).translateObject((float) Math.toRadians(0.1f) * -1, 0.0f, 0.0f);
        }
        // es krim gerak (I maju, K bawah)
        if (window.isKeyPressed(GLFW_KEY_I)) {
            eskrim.get(0).scaleObject(0.8f, 0.8f, 0.8f);
        }
        if (window.isKeyPressed(GLFW_KEY_K)) {
            eskrim.get(0).scaleObject(1.2f, 1.2f, 1.2f);
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
            glClearColor(0,0,0,0);
            GL.createCapabilities();
            input();

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
//            mixue.get(1).getChildObject().get(6).drawLine();
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