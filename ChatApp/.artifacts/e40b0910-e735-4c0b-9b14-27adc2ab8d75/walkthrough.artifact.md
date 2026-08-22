# Implementación de Chat Privado en Tiempo Real

Se ha completado la funcionalidad de mensajería privada entre usuarios, permitiendo una comunicación fluida y visualmente clara.

## Cambios Realizados

### [Infraestructura de Mensajería]
- **Modelo `Chat`**: Define la estructura de cada mensaje (`emisor`, `receptor`, `mensaje`).
- **Base de Datos**: Se utiliza el nodo `Chats` en Firebase Realtime Database para almacenar y sincronizar los mensajes instantáneamente.

### [Interfaz de Usuario (UI)]
- **Burbujas de Chat**: Se crearon diseños personalizados (`item_chat_izquierda.xml` y `item_chat_derecha.xml`) con colores diferenciados para identificar quién envió el mensaje.
- **Pantalla de Chat (`ChatActivity`)**:
    - Incluye un Toolbar que muestra el nombre del contacto actual.
    - Un `RecyclerView` que se desplaza automáticamente al último mensaje recibido.
    - Una barra inferior con entrada de texto y botón de envío.

### [Lógica y Navegación]
- **Acceso Directo**: Al tocar cualquier usuario en la lista principal, se abre su ventana de chat correspondiente.
- **Sincronización**: La aplicación escucha activamente los cambios en Firebase; cuando alguien envía un mensaje, este aparece en la pantalla del receptor sin necesidad de refrescar.

## Verificación
1. Selecciona a un usuario de tu lista de contactos.
2. Escribe un mensaje en la parte inferior y presiona el icono de enviar.
3. El mensaje aparecerá en una burbuja azul a la derecha.
4. Si recibes un mensaje, aparecerá en una burbuja gris a la izquierda.
