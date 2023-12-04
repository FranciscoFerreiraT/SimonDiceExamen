# DIAGRAMA DE FLUJO
graph TD;

Inicio-->Turno_Inicia_color_random
Turno_Inicia_color_random-->Genera_color_random
Genera_color_random-->Muestra_el_o_los_colores_random
Muestra_el_o_los_colores_random-->Correcto-->no
Correcto-->si-->Genera_color_random



# DIAGRAMA DE ESTADOç
graph TD;

Start-->Sequence
Sequence-->Wait
Wait-->Input
Input-->Checking
Checking-->|Error| GameOver
Checking-->|Correcto| Sequence


