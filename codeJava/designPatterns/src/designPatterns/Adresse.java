package designPatterns;

public class Adresse {
		private final String rue;
		private final String ville;
		private final String codepostal;
		private final String pays;
		
		public String getRue() {return rue;}
		public String getVille() {return ville;}
		public String getCodepostal() {return codepostal;}
		public String getPays() {return pays;}
		public Adresse(String rue, String ville, String codepostal, String pays) {
			super();
			this.rue = rue;
			this.ville = ville;
			this.codepostal = codepostal;
			this.pays = pays;
		}
		@Override
		public String toString() {
			return "Adresse [rue=" + rue + ", ville=" + ville + ", codepostal=" + codepostal + ", pays=" + pays + "]";
		}

		
		
}
