package Aplicacion;

/**
 * APLICACIÓN INTERES SIMPLE EN JAVA CON CONVERSIONES DE PERIODOS Y TASAS DE INTERES
 * @author Sebastian Burgos Puentes
 * 3016079417
 *
 */

import java.util.HashMap;
import java.util.Locale;

import javax.swing.JOptionPane;

public class AplicacionIE {

	private static String SALTO_LINEA = "\r\n";
	private static HashMap<Integer, Integer> unidades_tiempo = new HashMap<>();
	private static HashMap<Integer, String> ut_nombre_tasas = new HashMap<>();
	private static HashMap<Integer, String> ut_nombre_periodos = new HashMap<>();

	public static void main(String[] args) {
		initialize();
		try{
		run();
		}catch(Exception e){
			imprimir("Fin del Programa. Presione enter para salir ");
		}
	}

	private static void initialize() {
		unidades_tiempo.put(0, 1);
		unidades_tiempo.put(1, 2);
		unidades_tiempo.put(2, 3);
		unidades_tiempo.put(3, 4);
		unidades_tiempo.put(4, 6);
		unidades_tiempo.put(5, 12);
		unidades_tiempo.put(6, 24);
		unidades_tiempo.put(7, 52);
		unidades_tiempo.put(8, 365);

		ut_nombre_tasas.put(0, "Anual");
		ut_nombre_tasas.put(1, "Semestral");
		ut_nombre_tasas.put(2, "Cuatrimestral");
		ut_nombre_tasas.put(3, "Trimestral");
		ut_nombre_tasas.put(4, "Bimestral");
		ut_nombre_tasas.put(5, "Mensual");
		ut_nombre_tasas.put(6, "Bimensual");
		ut_nombre_tasas.put(7, "Semanal");
		ut_nombre_tasas.put(8, "Diario");
		ut_nombre_tasas.put(0, "Anual");

		ut_nombre_periodos.put(0, "Años");
		ut_nombre_periodos.put(1, "Semestres");
		ut_nombre_periodos.put(2, "Cuatrimestres");
		ut_nombre_periodos.put(3, "Trimestres");
		ut_nombre_periodos.put(4, "Bimestres");
		ut_nombre_periodos.put(5, "Meses");
		ut_nombre_periodos.put(6, "Bimensualidades");
		ut_nombre_periodos.put(7, "Semanas");
		ut_nombre_periodos.put(8, "Dias");


	}

	private static void run() {
		imprimir("Programa Interes Simple en JAVA con Conversion de Tasas y Unidades de Tiempo"+SALTO_LINEA+
				"Desarrollado por Sebastian Burgos Puentes"+SALTO_LINEA+
				"Programa: Ingeniería de Sistemas y Computación"+SALTO_LINEA+
				"Universidad del Quindío"+SALTO_LINEA+
				"Finalidad: Ingeniería Económica"+SALTO_LINEA+
				"Fecha de desarrollo: 05/11/2022 - 10/11/2022"+SALTO_LINEA+SALTO_LINEA+
				"INGRESE CUALQUIER TECLA PARA CONTINUAR");

		Integer tipo_ejercicio = Integer.valueOf(leer("Ingrese el tipo de ejercicio: "+SALTO_LINEA+
				"0: Interes Simple."+SALTO_LINEA+
				"1: Conversión de Tasas Para Interes Compuesto."
				));

		switch (tipo_ejercicio) {
		case 0:

			Integer busqueda_simple = Integer.valueOf(leer("¿Que desea calcular?: "+SALTO_LINEA+
					"0: El Valor Futuro (VF), Los intereses (I) y valor de intereses por periodo."+SALTO_LINEA+
					"1: El Valor Presente (VP) o Capital"+SALTO_LINEA+
					"2: La Tasa de Interes (i)"+SALTO_LINEA+
					"3: El tiempo invertido (n)"));

			switch (busqueda_simple) {
			case 0:
				ingreso_datos_interes_simple();
				break;

			case 1:
				ingreso_datos_interes_simple_capital();
				break;

			case 2:
				ingreso_datos_interes_simple_tasa();
				break;

			case 3:
				ingreso_datos_interes_simple_tiempo();
				break;
			default:
				run();
			}

			break;

		case 1:
			conversion_tasas();
			break;

		default:
			run();
		}
	}


	private static void conversion_tasas() {
		float tasa_interes = Float.valueOf(leer("Ingrese la tasa de interes:"));
		float tasa_convertida = tasa_interes;
		Integer t = 0;
		Integer tc = 0;


		Integer eleccion_conversion = Integer.valueOf(leer("Elija una opción: "+SALTO_LINEA+
							"0: Nominal -> Periodica"+SALTO_LINEA+
							"1: Periodica -> Nominal"+SALTO_LINEA+

							"2: Periodica Anticipada -> Periodica Vencida"+SALTO_LINEA+
							"3: Periodica Vencida -> Periodica Anticipada"+SALTO_LINEA+

							"4: Periodica Vencida -> Efectiva Anual"+SALTO_LINEA+
							"5: Efectiva Anual -> Periodica Vencida"+SALTO_LINEA+

							"6: Periodica Anticipada -> Efectiva Anual"+SALTO_LINEA+
							"7: Efectiva Anual -> Periodica Anticipada"+SALTO_LINEA+

							"8: Cambio de Periodo"));
		if (eleccion_conversion == 0) {
			t = leer_periodo_tasa_interes();
			tasa_convertida = (float) tasa_interes / (float) unidades_tiempo.get(t);
			imprimir("Tasa Original: "+tasa_interes+"% Nominal"+SALTO_LINEA+
					"Tasa Convertida: "+String.format(Locale.US,"%,.2f", (tasa_convertida))+"% " +ut_nombre_tasas.get(t));
		}
		if (eleccion_conversion == 1) {
			t = leer_periodo_tasa_interes();
			tasa_convertida = (float) tasa_interes * (float) unidades_tiempo.get(t);
			imprimir("Tasa Original: "+tasa_interes+"% " +ut_nombre_tasas.get(t)+SALTO_LINEA+
			         "Tasa Convertida: "+String.format(Locale.US,"%,.2f", (tasa_convertida))+"% Nominal");
		}
		if (eleccion_conversion == 2) {
			t = leer_periodo_tasa_interes();
			tasa_convertida = (float) (tasa_interes/100) / (1 - (float) (tasa_interes/100));
			imprimir("Tasa Original: "+tasa_interes+"% " +ut_nombre_tasas.get(t)+ " Anticipada"+SALTO_LINEA+
			         "Tasa Convertida: "+String.format(Locale.US,"%,.2f", (tasa_convertida*100))+"% " +ut_nombre_tasas.get(t) + " Vencida");
		}
		if (eleccion_conversion == 3) {
			t = leer_periodo_tasa_interes();
			tasa_convertida = (float) (tasa_interes/100) / (1 + (float) (tasa_interes/100));
			imprimir("Tasa Original: "+tasa_interes+"% " +ut_nombre_tasas.get(t)+ " Vencida"+SALTO_LINEA+
			         "Tasa Convertida: "+String.format(Locale.US,"%,.2f", (tasa_convertida*100))+"% " +ut_nombre_tasas.get(t) + " Anticipada");
		}
		if (eleccion_conversion == 4) {
			t = leer_periodo_tasa_interes();
			float n = unidades_tiempo.get(t);
			tasa_convertida = (float) Math.pow((1+((float)tasa_interes/100)), n) - 1;
			imprimir("Tasa Original: "+tasa_interes+"% " +ut_nombre_tasas.get(t)+ " Vencida"+SALTO_LINEA+
			         "Tasa Convertida: "+String.format(Locale.US,"%,.2f", (tasa_convertida*100))+"% Efectiva Anual");
		}
		if (eleccion_conversion == 5) {
			t = leer_periodo_tasa_interes();
			float n = unidades_tiempo.get(t);
			tasa_convertida = (float) Math.pow((1+((float)tasa_interes/100)), (1/n)) - 1;
			imprimir("Tasa Original: "+tasa_interes+"% Efectiva Anual"+SALTO_LINEA+
			         "Tasa Convertida: "+String.format(Locale.US,"%,.2f", (tasa_convertida*100))+"% "+ut_nombre_tasas.get(t)+ " Vencida");
		}
		if (eleccion_conversion == 6) {
			t = leer_periodo_tasa_interes();
			float n = unidades_tiempo.get(t);
			tasa_convertida = (float) (Math.pow((1 - (float) tasa_interes/100), -n) - 1);
			imprimir("Tasa Original: "+tasa_interes+"%" + ut_nombre_tasas.get(t)+ " Anticipada"+SALTO_LINEA+
			         "Tasa Convertida: "+String.format(Locale.US,"%,.2f", (tasa_convertida*100))+"% Efectiva Anual");
		}
		if (eleccion_conversion == 7) {
			t = leer_periodo_tasa_interes();
			float n = unidades_tiempo.get(t);
			tasa_convertida = (float) (1 - (Math.pow((1 + (float) tasa_interes/100), -(1/n))));
			imprimir("Tasa Original: "+String.format(Locale.US,"%,.2f", (tasa_convertida*100))+"% Efectiva Anual"+SALTO_LINEA+
					"Tasa Convertida: "+tasa_interes+"%" + ut_nombre_tasas.get(t)+ " Anticipada");
		}
		if (eleccion_conversion == 8) {
			t = leer_periodo_tasa_interes();
			imprimir("Ahora ingrese el periodo al que la quiere convertir");
			tc = leer_periodo_tasa_interes();
			float n1 = unidades_tiempo.get(t);
			float n2 = unidades_tiempo.get(tc);
			tasa_convertida = (float) (Math.pow((1+(float)tasa_interes/100), (n1/n2)) - 1);
			imprimir("Tasa Convertida: "+tasa_interes+"%" + ut_nombre_tasas.get(t)+SALTO_LINEA+
					"Tasa Convertida: "+String.format(Locale.US,"%,.2f", (tasa_convertida*100))+"%" + ut_nombre_tasas.get(tc));
		}

		run();
	}

	private static void ingreso_datos_interes_simple_tiempo() {
		float vp = Float.valueOf(leer("Valor Presente (VP): "));
		float tasa_interes = Float.valueOf(leer("Tasa de Interes (i)"+SALTO_LINEA+"NOTA: usar . no , : "));
		Integer periodo_interes = leer_periodo_tasa_interes();

		float intereses = Float.valueOf(leer("Intereses (I): "));

		float n = intereses/(vp * ((tasa_interes)/100));
		n = n * unidades_tiempo.get(periodo_interes);

		String resultado =  "DATOS INGRESADOS:"+SALTO_LINEA+
				"Valor Presente (VP): $"+String.format(Locale.US,"%,.2f", vp)+ SALTO_LINEA +
				"Tasa de Interes: "+String.format(Locale.US,"%,.2f", tasa_interes)+"% "+ut_nombre_tasas.get(periodo_interes)+ SALTO_LINEA +
				"Intereses Pagados (I): $"+ String.format(Locale.US,"%,.2f", intereses)+ SALTO_LINEA + SALTO_LINEA +
				"RESULTADOS"+ SALTO_LINEA +
				"Tiempo (n): "+ String.format(Locale.US,"%,.2f", n) + " " + ut_nombre_periodos.get(periodo_interes);

		imprimir(resultado);
	}

	private static void ingreso_datos_interes_simple_tasa() {
		float vp = Float.valueOf(leer("Valor Presente (VP): "));
		float vf = Float.valueOf(leer("Valor Futuro (VF): "));
		float n = Float.valueOf(leer("Tiempo (n): "));
		Integer unidad_tiempo_prestamo = leer_unidad_tiempo();
		float interes = vf - vp;

		float tasa = (interes/((float)vp * (float)n))*100;

		String resultado = "DATOS INGRESADOS:"+SALTO_LINEA+
							"Valor Presente (VP): $"+String.format(Locale.US,"%,.2f", vp)+ SALTO_LINEA +
							"Valor Final (VP): $"+ String.format(Locale.US,"%,.2f", vf)+ SALTO_LINEA +
							"Tiempo (n): "+ String.format(Locale.US,"%,.1f", n)+ SALTO_LINEA + SALTO_LINEA +
							"RESULTADOS"+ SALTO_LINEA +
							"Intereses Pagados (I): $"+ String.format(Locale.US,"%,.2f", interes)+ SALTO_LINEA +
							"Tasa de Interes: "+String.format(Locale.US,"%,.2f", tasa)+"% "+ut_nombre_tasas.get(unidad_tiempo_prestamo);
		imprimir(resultado);
	}

	private static void ingreso_datos_interes_simple_capital() {
		float n = Float.valueOf(leer("Tiempo (n): "));
		Integer unidad_tiempo_prestamo = leer_unidad_tiempo();
		float intereses_total = Float.valueOf(leer("Total Interes(I): "));
		float tasa_interes = Float.valueOf(leer("Tasa de Interes (i)"+SALTO_LINEA+"NOTA: usar . no , : "));
		Integer periodo_interes = leer_periodo_tasa_interes();
		float tasa_convertida = convertir_tasa_simple(tasa_interes, periodo_interes, unidad_tiempo_prestamo);

		float capital = (float)intereses_total/(n*((float)tasa_convertida/100));

		String resultado = "DATOS INGRESADOS:"+SALTO_LINEA+
							"Tiempo (n): "+n+SALTO_LINEA+
							"Total Intereses (I): $"+intereses_total+SALTO_LINEA+SALTO_LINEA+
							"RESULTADO"+SALTO_LINEA+
							"El Capital (VP): $"+String.format(Locale.US,"%,.2f", capital);
		imprimir(resultado);
		run();
	}

	private static void ingreso_datos_interes_simple() {
		float vp = Float.valueOf(leer("Valor Presente (Vp): "));
		float n = Float.valueOf(leer("Tiempo (n): "));
		Integer unidad_tiempo_prestamo = leer_unidad_tiempo();
		float tasa_interes = Float.valueOf(leer("Tasa de Interes (i)"+SALTO_LINEA+"NOTA: usar . no , : "));
		Integer periodo_interes = leer_periodo_tasa_interes();
		Integer unidad_tiempo_pago_intereses = leer_periodo_pago_intereses();
		calcular_interes_simple(vp, n, unidad_tiempo_prestamo, unidad_tiempo_pago_intereses, tasa_interes, periodo_interes);
	}

	private static Integer leer_periodo_pago_intereses() {
		return Integer.valueOf(leer("Periodo en que se va a pagar los intereses: "+SALTO_LINEA+
				"0: Anual"+SALTO_LINEA+
				"1: Semestral"+SALTO_LINEA+
				"2: Cuatrimestral"+SALTO_LINEA+
				"3: Trimestral"+SALTO_LINEA+
				"4: Bimestral"+SALTO_LINEA+
				"5: Mensual"+SALTO_LINEA+
				"6: Bimensual"+SALTO_LINEA+
				"7: Semanal"+SALTO_LINEA+
				"8: Diario"));
	}

	private static Integer leer_periodo_tasa_interes() {
		return Integer.valueOf(leer("Periodo de tasa de interes: "+SALTO_LINEA+
				"0: Anual"+SALTO_LINEA+
				"1: Semestral"+SALTO_LINEA+
				"2: Cuatrimestral"+SALTO_LINEA+
				"3: Trimestral"+SALTO_LINEA+
				"4: Bimestral"+SALTO_LINEA+
				"5: Mensual"+SALTO_LINEA+
				"6: Bimensual"+SALTO_LINEA+
				"7: Semanal"+SALTO_LINEA+
				"8: Diario"));
	}

	private static Integer leer_unidad_tiempo() {
		return Integer.valueOf(leer("Periodo: "+SALTO_LINEA+
				"0: Años (1 por año)"+SALTO_LINEA+
				"1: Semestres (2 por año)"+SALTO_LINEA+
				"2: Cuatrimestres (3 por año)"+SALTO_LINEA+
				"3: Trimestres (4 por año)"+SALTO_LINEA+
				"4: Bimestres (6 por año)"+SALTO_LINEA+
				"5: Meses (12 por año)"+SALTO_LINEA+
				"6: Quincenas / Bimensualidades (24 por año)"+SALTO_LINEA+
				"7: Semanas (52 por año)"+SALTO_LINEA+
				"8: Dias (365 por año)"));
	}

	private static void calcular_interes_simple(float vp, float n, Integer unidad_tiempo,
			Integer unidad_tiempo_pago_intereses, float tasa_interes,Integer periodo_interes) {

		Float tasa_convertida = convertir_tasa_simple(tasa_interes, periodo_interes, unidad_tiempo_pago_intereses);

		Float valor_interes_por_periodo = (tasa_convertida/100) * vp;

		Float nuevo_n = n * ((float)unidades_tiempo.get(unidad_tiempo_pago_intereses) / (float)unidades_tiempo.get(unidad_tiempo));

		Float valor_intereses_total = vp * (tasa_convertida/100) * nuevo_n;

		Float valor_futuro_pagar = vp * (1 + (tasa_convertida/100) * nuevo_n);

		String resultado = "DATOS INGRESADOS:"+SALTO_LINEA+
				"Valor Presente (VP): $"+String.format(Locale.US,"%,.2f", vp)+SALTO_LINEA+
				"Tiempo: "+String.format(Locale.US,"%,.1f", n)+" "+ut_nombre_periodos.get(unidad_tiempo)+SALTO_LINEA+
				"Tasa de interes: "+String.format(Locale.US,"%,.2f", tasa_interes)+"% "+ut_nombre_tasas.get(periodo_interes)+SALTO_LINEA+
				"El interes se pagará en periodos: "+ut_nombre_tasas.get(unidad_tiempo_pago_intereses)+SALTO_LINEA+SALTO_LINEA+
				"RESULTADOS"+SALTO_LINEA+
				"Tasa de interes convertida: "+ String.format(Locale.US,"%,.3f", tasa_convertida) +"% " + ut_nombre_tasas.get(unidad_tiempo_pago_intereses) + SALTO_LINEA +
				"Interes a pagar por periodo: $"+ String.format(Locale.US,"%,.2f", valor_interes_por_periodo)+" "+ut_nombre_tasas.get(unidad_tiempo_pago_intereses) + SALTO_LINEA +
				"Intereses totales (I): $"+ String.format(Locale.US,"%,.2f", valor_intereses_total)+ SALTO_LINEA +
				"Valor total a pagar despues de "+n+" periodos "+ut_nombre_tasas.get(unidad_tiempo)+" (VF): $"+ String.format(Locale.US,"%,.2f", valor_futuro_pagar)+SALTO_LINEA+
				"El capital a pagar (VP) sigue siendo: $"+String.format(Locale.US,"%,.2f", vp)+SALTO_LINEA+
				"Presione enter para salir. ";

		imprimir(resultado);
		run();
	}

	private static Float convertir_tasa_simple(float tasa_interes, Integer periodo_interes,
			Integer unidad_tiempo_pago_intereses) {
		int p1 = unidades_tiempo.get(periodo_interes);
		int p2 = unidades_tiempo.get(unidad_tiempo_pago_intereses);
		return tasa_interes * ((float)p1/(float)p2);
	}

	private static String leer(String string) {
		return JOptionPane.showInputDialog(string);
	}

	private static void imprimir(Object mensaje) {
		JOptionPane.showInputDialog(mensaje);
	}

}
