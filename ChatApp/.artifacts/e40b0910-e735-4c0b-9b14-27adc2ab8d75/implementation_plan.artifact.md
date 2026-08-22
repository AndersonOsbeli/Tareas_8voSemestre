# Plan de Implementación: Interfaz y Lógica de Chat

El objetivo es permitir que los usuarios inicien una conversación al seleccionar a alguien de la lista, incluyendo el diseño de la burbuja de mensajes y la sincronización en tiempo real.

## User Review Required

> [!IMPORTANT]
> Se implementará una estructura de base de datos en Firebase bajo el nodo `Chats` para almacenar los mensajes entre usuarios.

## Proposed Changes

### [Navegación]

#### [MODIFY] [AdapterUsuario.kt](file:///C:/Users/osbel/OneDrive/Escritorio/app_android/ChatApp/app/src/main/java/com/example/chatapp/adapter/AdapterUsuario.kt)
- Añadir un `setOnClickListener` en `onBindViewHolder` para abrir `ChatActivity` pasando el `uid` del usuario seleccionado.

### [Modelos y Adaptadores]

#### [NEW] [Chat.kt](file:///C:/Users/osbel/OneDrive/Escritorio/app_android/ChatApp/app/src/main/java/com/example/chatapp/model/Chat.kt)
- Modelo para los mensajes: `emisor`, `receptor`, `mensaje`.

#### [NEW] [AdapterChat.kt](file:///C:/Users/osbel/OneDrive/Escritorio/app_android/ChatApp/app/src/main/java/com/example/chatapp/adapter/AdapterChat.kt)
- Adaptador que maneja dos tipos de vista: una para mensajes enviados (derecha) y otra para recibidos (izquierda).

### [Diseño de UI]

#### [NEW] [activity_chat.xml](file:///C:/Users/osbel/OneDrive/Escritorio/app_android/ChatApp/app/src/main/res/layout/activity_chat.xml)
- Pantalla con Toolbar (nombre y foto del contacto), `RecyclerView` para los mensajes y un `EditText` con botón de enviar en la parte inferior.

#### [NEW] [item_chat_izquierda.xml](file:///C:/Users/osbel/OneDrive/Escritorio/app_android/ChatApp/app/src/main/res/layout/item_chat_izquierda.xml) / [item_chat_derecha.xml](file:///C:/Users/osbel/OneDrive/Escritorio/app_android/ChatApp/app/src/main/res/layout/item_chat_derecha.xml)
- Diseños de burbujas de chat para diferenciar visualmente quién envía el mensaje.

### [Lógica de Mensajería]

#### [NEW] [ChatActivity.kt](file:///C:/Users/osbel/OneDrive/Escritorio/app_android/ChatApp/app/src/main/java/com/example/chatapp/ChatActivity.kt)
- Inicializar Firebase y vistas.
- Implementar `EnviarMensaje(emisor, receptor, mensaje)`.
- Implementar `LeerMensajes(misId, suId)` para escuchar cambios en la base de datos y actualizar la lista en tiempo real.

## Verification Plan

### Manual Verification
1. En la lista de usuarios, hacer clic en un usuario.
2. Verificar que se abra la pantalla de chat con el nombre correcto en la parte superior.
3. Enviar un mensaje y verificar que aparezca en el lado derecho.
4. (Opcional) Abrir la app en otro dispositivo o simulador y responder; verificar que el mensaje aparezca en el lado izquierdo instantáneamente.
