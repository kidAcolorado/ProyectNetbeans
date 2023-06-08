package zoo;

public class Dni {

	private int numero;
	private String letra;

	public Dni(int numero, String letra) {
		this.numero = numero;
		this.letra = letra;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	@Override
	public String toString() {
		return "Dni{" + "numero=" + numero + ", letra=" + letra + '}';
	}

}
