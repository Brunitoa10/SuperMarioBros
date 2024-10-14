package Entidades;

import Fabricas.Sprite;
import Visitor.Visitor;

public class Silueta extends Entidad{
	
	public Silueta(Sprite mi_sprite) {
		super(0, 0,mi_sprite);
	}

	@Override
	public boolean detectColision(Entidad c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
}
