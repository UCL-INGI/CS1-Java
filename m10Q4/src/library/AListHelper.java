/**
 *  Copyright (c) 2017 Brandon Naitali
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package src.library;

import static student.Translations.Translator._;
import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Random;

import src.library.*;
import src.Correction;
import StudentCode.*;

public class AListHelper {

	/**
	*	Returns a new identical instance of l
	**/
	public static Etudiant newInstance(Etudiant l){
		Etudiant e = new Etudiant();
		for(int i = 0; i < l.length(); i++){
			Correction.add(e, i, Correction.get(l, i));	
		}
		return e;
	}
	
	/**
	*	Returns true if the two array of Object contains the same objects (with equals), managing null object;
			false otherwise;
	**/
	public static boolean objectArrayEquality(Object [] a, Object [] b){
		if(a.length!=b.length) return false;
		for(int i = 0; i < a.length; i++){
			if(a[i]==null && b[i]==null) continue;
			if(a[i]==null && b[i]!=null || a[i]!=null && b[i]==null) return false;
			if(!a[i].equals(b[i])) return false;
		}
		return true;
	}

}
