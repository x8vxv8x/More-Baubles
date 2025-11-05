package smd.morebaubles.baubleeffect;

import java.util.UUID;

public interface IFireResistance {
	default float getResistance() {
		return 1.0F;
	}

	default float getResistanceLava() {
		return 0.0F;
	}

	default float getMaxNegate() {
		return 0.0F;
	}

	UUID getFireResistID();
}
