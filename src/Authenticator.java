import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.DatatypeConverter;

public class Authenticator {

	private static final String PASSWORD_ENCODED = "824949B53942423F4263F3464332A699EFECDD28E4B58D4450F3C54BE37C938D";
	private static boolean flag=false;

	/**
	 * Másik módszer a konverzióra (javax.xml.bind.DatatypeConverter)
	 * ez kicsit rövidebb kódot eredményezß
	 
	public static byte[] ahexStringToByteArray(String s) {
	    return DatatypeConverter.parseHexBinary(s);
	}
	**/
	
	public static byte[] hexStringToByteArray(String s) {
		   int len = s.length();
		   byte[] data = new byte[len / 2];
		   for (int i = 0; i < len; i += 2){
		   data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
		   + Character.digit(s.charAt(i+1), 16));
		   }
		   return data;
		  }

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		byte[] pw = {'j', 'a', 'a', 'a', 'a', 'a', 'a', 'k'};
		byte[] pwencbytes = hexStringToByteArray(PASSWORD_ENCODED);
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		LOOP:
			for (int i1 = 0; i1 < 26; i1++) {
				System.out.println("checking " + new String(pw));
				for (int i2 = 0; i2 < 26; i2++) {
					for (int i3 = 0; i3 < 26; i3++) {
						for (int i4 = 0; i4 < 26; i4++) {
							for (int i5 = 0; i5 < 26; i5++) {
								for (int i6 = 0; i6 < 26; i6++) {
									if (Arrays.equals(pwencbytes, md.digest(pw))) {
										System.out.println("+++++++FOUND IT!!!!!!+++++++  " + new String(pw));
										break LOOP;
									}
									pw[6]++;
								}
								pw[6] = 'a';
								pw[5]++;
							}
							pw[5] = 'a';
							pw[4]++;
						}
						pw[4] = 'a';
						pw[3]++;
					}
					pw[3] = 'a';
					pw[2]++;
				}
				pw[2] = 'a';
				pw[1]++;
			}
	}

	public static boolean checkPassword(String password) {
		try {
			if (password.length() != 8) {
				throw new IllegalArgumentException();
			}

			if (!password.equals(password.toLowerCase())) {
				throw new IllegalArgumentException();
			}

			for (char c : password.toCharArray()) {
				if (c < 97 || c > 122) {
					throw new IllegalArgumentException();
				}
			}

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] digest = md.digest(password.getBytes());
			String hex = new HexBinaryAdapter().marshal(digest);

			return hex.equals(PASSWORD_ENCODED);
		} catch (Exception e) {
			return false;
		}
	}

}