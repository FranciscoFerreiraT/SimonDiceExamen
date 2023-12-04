# DIAGRAMA DE FLUJO
graph TD

A[Inicio] --> B[Start/Reset]
B-->C
C[ColorEnviado + Colores]-->D[+Color]
D-->E
E["Colores"]-->F[WaitColoresPulsados]
F-->G
G[AumentarColores]-->H{BotonEnviadoPulsado}
H-->|NO|E
H-->|SÍ|I[enviarDatos]
I-->J{Colores==ColorEnviado}
J-->|NO|K[Perdiste]
K-->L[FIN]
J-->|SÍ|M[aumentarRonda]
M-->C

# DIAGRAMA DE ESTADO
graph TD

A[START]-->B[SECUENCIA]
-->C[WAIT]
-->D[INPUT]
-->E[COMPROBAR]
E-->|SÍ|B
E-->|NO|F[PERDER]-->A
