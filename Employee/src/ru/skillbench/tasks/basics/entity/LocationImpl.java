package ru.skillbench.tasks.basics.entity;

//import ru.skillbench.tasks.basics.entity.Location.Type;

public class LocationImpl implements Location{
	private String name = new String();
	private Type type;
	private Location parent;
	public LocationImpl() {};
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public Type getType() {
		return type;
	}
	@Override
	public void setType(Type type) {
		this.type = type;
	}
	@Override
	public void setParent(Location parent) {
		this.parent = parent;
	}
	@Override
	public String getParentName() {
		if(parent == null){
			return "--";
		}
		return parent.getName();
	}
	@Override
	public Location getTopLocation() {
		if (parent == null) {
			return this;
		}
		else {
			return parent.getTopLocation();
		}
	}
	@Override
	public boolean isCorrect() {
		if (this.parent == null) {
		return true;
		}
		else if(this.parent.getType().compareTo(this.getType()) >= 0) {
			return false;
		}
		else {
			return this.parent.isCorrect();
		}
	}
	@Override
	public String getAddress() {
		String tmp = new String();
		if (this.parent != null) {
			 tmp = this.parent.getAddress();
		}
		else {
			for (int i = 0; i < this.name.length(); i++) {
				if (this.name.charAt(i) == ' ') {
					return this.type.getNameForAddress() + this.getName();
				}
				if (this.name.charAt(i) == '.') {
					return this.name;
				}
			}
			return this.type.getNameForAddress() + this.getName();
		}
		for (int i = 0; i < this.name.length(); i++) {
			if (this.name.charAt(i) == ' ') {
				return this.type.getNameForAddress() + this.name + ", " + tmp;
			}
			if (this.name.charAt(i) == '.') {
				return this.name + ", " + tmp;
			}
		}
		return this.type.getNameForAddress() + this.name + ", " + tmp;
	}
	@Override
	public String toString() {
		return this.name + " (" + this.type.toString() + ")";
	}
}
