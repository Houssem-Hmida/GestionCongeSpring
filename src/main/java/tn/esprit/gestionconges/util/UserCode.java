package tn.esprit.gestionconges.util;

import java.util.UUID;

public class UserCode {
public static String getcode() {
	return UUID.randomUUID().toString();
}
}
