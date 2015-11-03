import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class Authenticator {

	private static final String PASSWORD_ENCODED = "824949B53942423F4263F3464332A699EFECDD28E4B58D4450F3C54BE37C938D";
	private static boolean flag=false;

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		/***if (checkPassword(args[0])) {
			System.out.println("Access granted!");
		} else {
			System.out.println("Access denied!");
		}**/
		byte[] array = {'b', 'a', 'a', 'a', 'a', 'a', 'a', 'c'}; 
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		EXIT:
			for (int i=0; i<26; i++){
				System.out.println(new String(array,"UTF-8"));
				array[1]++;
				for (int i2=0; i2<26; i2++){
					//System.out.println(new String(array,"UTF-8"));
					array[2]++;
					for (int i3=0; i3<26; i3++){
						//System.out.println(new String(array,"UTF-8"));
						array[3]++;
						for (int i4=0; i4<26; i4++){
							//System.out.println(new String(array,"UTF-8"));
							array[4]++;
							for (int i5=0; i5<26; i5++){
								//System.out.println(new String(array,"UTF-8"));
								array[5]++;
								for (int i6=0; i6<26; i6++){
									//System.out.println(new String(array,"UTF-8"));
									array[6]++;
									byte[] digest = md.digest(array);
									String hex = new HexBinaryAdapter().marshal(digest);
									if (hex.equals(PASSWORD_ENCODED)){
										System.out.println("password is "+array);
										flag=true;
										break EXIT;
									}
								}
								array[6]='a';
							}
							array[5]='a';
						}
						array[4]='a';
					}
					array[3]='a';
				}
				array[2]='a';
			}

		if (!flag)
			System.out.println(new String(array,"UTF-8")+" nem jo");
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