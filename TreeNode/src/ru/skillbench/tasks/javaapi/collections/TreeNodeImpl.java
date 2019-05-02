package ru.skillbench.tasks.javaapi.collections;

import java.util.*;

public class TreeNodeImpl implements TreeNode {
	private TreeNode parent;
	private boolean isExpanded = false;
	private Object data;

	private Set<TreeNode> childrens = new HashSet<TreeNode>();

	public TreeNodeImpl() {
		
	}

	@Override
	public TreeNode getParent() {
		return this.parent;
	}

	@Override
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	@Override
	public TreeNode getRoot() {
		if (this.parent == null) {
			return null;
		}
		TreeNode ptr = this;
		while (ptr.getParent() != null) {
			ptr = ptr.getParent();
		}
		return ptr;
	}
	
	@Override
	public boolean isLeaf() {
		if (this.childrens == null || this.childrens.size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int getChildCount() {
		if(this.childrens == null) {
			return 0;
		}
		else {
			return this.childrens.size();		
		}
	}

	@Override
	public Iterator<TreeNode> getChildrenIterator() {
		if(this.childrens != null) {
			return this.childrens.iterator();
		}
		return null;
	}

	@Override
	public void addChild(TreeNode child) {
		child.setParent(this);
		this.childrens.add(child);
	}

	@Override
	public boolean removeChild(TreeNode child) {
		child.setParent(null);
		if(this.childrens != null) {
			return this.childrens.remove(child);
		}
		return false;
	}

	@Override
	public boolean isExpanded() {
		return this.isExpanded;
	}

	@Override
	public void setExpanded(boolean expanded) {
		this.isExpanded = expanded;
		Iterator<TreeNode> it = this.childrens.iterator();
		while (it.hasNext()) {
			it.next().setExpanded(expanded);
		}
	}
	@Override
	public Object getData() {
		return this.data;
	}
	@Override
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String getTreePath() {
		String res = "";
		TreeNode ptr = this;
		while (ptr.getParent() != null) {
			res = makePath(ptr, res);
			ptr = ptr.getParent();
		}
		res = makePath(ptr, res);
		res = res.substring(0, res.length() - 2);
		return res; 
	}
	private String makePath(TreeNode ptr, String res) {
		String dataString = "";
		if (ptr.getData() == null) {
			dataString = "empty";
		} else {
			dataString = ptr.getData().toString();
		} 
		return dataString + "->" + res;
	}
	@Override
	public TreeNode findParent(Object data) {
		if (this.data == null) {
			return this;
		}
		if (this.data.equals(data)) {
			return this;
		} 
		if(this.getParent() != null) {
			return this.getParent().findParent(data);
		}
		else {
			return null;
		}
	}

	@Override
	public TreeNode findChild(Object data) {
		if(this.childrens != null) {
			if (this.data == null && data == null) {
				return this;
			}	
			if (this.data != null && this.data.equals(data)) {
				return this;
			}
			for (TreeNode item : childrens) {
				TreeNode tmp = item.findChild(data);
				if(tmp != null) {
					return tmp;
				}
			}
		}
		return null;
	}

}
