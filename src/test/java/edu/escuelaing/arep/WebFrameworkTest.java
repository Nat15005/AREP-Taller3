package edu.escuelaing.arep;

import edu.escuelaing.arep.server.WebFramework;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class WebFrameworkTest {

    @BeforeEach
    void setUp() {
        // Reiniciar la carpeta estática antes de cada prueba
        WebFramework.staticfiles("src/main/resources/static");
        // Limpiar las rutas GET registradas
        WebFramework.getRoutes.clear();
    }

    @Test
    void testStaticFilesConfiguration() {
        String newStaticFolder = "src/main/resources/new_static";
        WebFramework.staticfiles(newStaticFolder);
        assertEquals(newStaticFolder, WebFramework.getStaticFolder(), "La carpeta estática debería ser " + newStaticFolder);
    }

    @Test
    void testGetRouteRegistration() {
        WebFramework.get("/test", (req, res) -> "Hello, World!");
        assertTrue(WebFramework.getRoutes.containsKey("/test"), "La ruta '/test' debería estar registrada");
    }

    @Test
    void testHandleGetRequest() throws IOException {
        // Registrar una ruta GET
        WebFramework.get("/hello", (req, res) -> "Hello, " + req.getValues("name"));

        // Simular una solicitud GET
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", "Pedro");

        WebFramework.handleRequest("GET", "/hello", queryParams, out);

        // Verificar la respuesta
        String response = out.toString();
        assertTrue(response.contains("HTTP/1.1 200 OK"), "La respuesta debería ser 200 OK");
        assertTrue(response.contains("Hello, Pedro"), "La respuesta debería contener 'Hello, Pedro'");
    }

    @Test
    void testHandleStaticFileRequest() throws IOException {
        // Simular una solicitud a un archivo estático
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        WebFramework.handleRequest("GET", "/index.html", new HashMap<>(), out);

        // Verificar que la respuesta contenga el encabezado 200 OK
        String response = out.toString();
        assertTrue(response.contains("HTTP/1.1 200 OK"), "La respuesta debería ser 200 OK");
        assertTrue(response.contains("Content-Type: text/html"), "La respuesta debería contener el tipo de contenido HTML");
    }

    @Test
    void testHandleNonExistentRoute() throws IOException {
        // Simular una solicitud a una ruta no registrada
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        WebFramework.handleRequest("GET", "/nonexistent", new HashMap<>(), out);

        // Verificar que la respuesta contenga el encabezado 404 Not Found
        String response = out.toString();
        assertTrue(response.contains("HTTP/1.1 404 Not Found"), "La respuesta debería ser 404 Not Found");
    }
}