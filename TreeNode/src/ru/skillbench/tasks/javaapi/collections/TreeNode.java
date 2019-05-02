package ru.skillbench.tasks.javaapi.collections;

import java.util.Iterator;
import java.util.Set;

/**
 * ���� ������<ul>
 * <li>���������������� � ���������� �������� �������� � ����������� ���������� ������.</li>
 * <li>��������� ������������ ����� �� ��������� (�� �����).</li>
 * <li>������������� � ��������� �������������� Iterator � Chain of Responsibility.</li>
 * </ul>
 * <p/>
 * �������<br/>
 * ����������� ����� �������, ��������������� ����� ���� ���������� ������ (��������, UI-���������� "������").<br/>
 * <p/>
 * ������ ������� ��������� ������: <a href="http://www.primefaces.org/docs/api/5.1/org/primefaces/model/DefaultTreeNode.html">
 *  DefaultTreeNode in JSF Primefaces</a><br/>
 * <p/>
 * ������������<br/>
 * ��������� �������� ����� ������� ���� (children) ������������� ������������������ ���� ��� �������������, 
 *   �.�. ��� ���������� ��������� ���� (������ "lazy initialization"). ��� ������, ��� ��������� � children getter-������ 
 *   (������� {@link #isLeaf()}) �� ������ �������� �����, ��������� ��� ������������� ������ ���������.<br/>
 * <p/>
 * ����������<br/>
 * ������������ �������������� ����� {@link #equals(Object)} � ����� ������: ��� ����� �������� � ������� ��������.<br/>
 * 
 * @author Georgii Kolpakov
 * @author Alexey Evdokimov
 */
public interface TreeNode { 
    /**
     * ���������� ������������ ������ <code>TreeNode</code>.
     */
    TreeNode getParent();
    /**
     * ������ ������������ ������ <code>TreeNode</code>.<br/>
     * ������ ���������� � ������� {@link #addChild(TreeNode)} � {@link #removeChild(TreeNode)}
     *  ������������� ������� <code>TreeNode</code>.
     */
    void setParent(TreeNode parent);
    /**
     * ���������� ������ ������, ����������� ������ ������ <code>TreeNode</code>.
     * @return �������� ����. ��� <code>null</code>, ���� � ������� ���� ��� ��������.
     */
    TreeNode getRoot();

    /**
     * ���������� <code>false</code>, ���� <code>TreeNode</code> ����� ��������� ����� �������� �����.
     * @return <code>true</code>, ���� ������ ���� �������� �������� (�.�. �� ����� �������� �����)
     */
    boolean isLeaf();
    /**
     * ���������� ����� �������� ����� ������� <code>TreeNode</code>.
     */
    int getChildCount();
    /**
     * ���������� �������� ���� ������� <code>TreeNode</code> � ���� {@link Iterator}.
     */
    Iterator<TreeNode> getChildrenIterator();
    /**
     * ��������� ��������� � ��������� <code>TreeNode</code> � �������� ��������� ����
     *  � ������ ��� � �������� �������� <code>this</code>.
     */
    void addChild(TreeNode child);
    /**
     * ������� ��������� � ��������� <code>TreeNode</code> �� ��������� �������� ����� � (���� �������)
     *  ������ ��� � �������� �������� <code>null</code> (����� ���������� ������������� ��������� ������).<br/>
     * ���� ������ � ��������� � ��������� �� ��� �� �������� ����� ���������: <br/>
     * �) �����/�������� �������, ���� � ��� ������� ������, ������ ���������� � ������ ������ {@link Object#equals(Object)};<br/>
     * �) ���� � ��������� ��������� ����� �������� (���� ��� �� {@link Set}), �� ��������� ������ ����.
     * @return <code>true</code> - ���� �������� �������, <code>false</code> - ���� ����� �������� ���� �� ������.
     */
    boolean removeChild(TreeNode child);
    
    /**
     * ���������� ������� "������������� / �����������" ������� <code>TreeNode</code>
     *  (� UI-����������� ���� "������" �� ����� ������� ������ � ����� �������� �����).<br/>
     * ���� "�������" �� ��������� - �� ����, ���� {@link #setExpanded(boolean)} �� ���������.
     * @return <code>true</code> - ���� ���� ���������, <code>false</code></code></code> - ���� ���� "�������" (collapsed)

     */
    boolean isExpanded();
    /**
     * ������ ������� "�������������" (expanded) ������� <code>TreeNode</code> � ���������� ���� ��� �������� �����
     * @param expanded <code>true</code> - ������������� ��� ����� ������, <code>false</code> - ����������� ��.
     */
    void setExpanded(boolean expanded);
    
    /**
     * @return "����������������" (�������� �����) ������ ������, ���������� � ���� <code>TreeNode</code>.
     *  ��� <code>null</code>, ���� {@link #setData(Object)} �� ���������.
     */
    Object getData();
    /**
     * ������ "����������������" ������ ������ ��� �������� � ���� <code>TreeNode</code>.
     */
    void setData(Object data);
    /**
     * ���������� ��������� ������������� ���� �� ����� ������ �� ������� <code>TreeNode</code>.<br/>
     * �������� ���� ����������� ��������� "->".<br/>
     * ������ ������� ���� - ��� ���� getData().toString(), ���� ������ "empty", ���� getData()==null.<br/>
     * ��������: "rootNode0->node1->node13->empty" ("rootNode0" - ��� � ������ ������� ��������� ������ ������
     *  getRoot().getData().toString() ).
     */
    String getTreePath();
    /**
     * ����� ������� ������������ ����� ������� <code>TreeNode</code> ����� ������� (������) ���� � �������� �������� <code>data</code>.<br/>
     * �� ����������, "������� ������������ �����" �������� ��� ������ ���� (�� ����, �������� ���������: obj.findParent(*) == obj).<br/>
     * ������� <code>data</code> ������ ������������ � ������� {@link Object#equals(Object)}, � ���� <code>data == null</code>,
     *  ����� ������ ������������ ������������ ����, � �������� <code>getData() == null</code>).
     * @param data ������ ������; ����� ���� ����� <code>null</code>
     * @return ��������� ����. ��� <code>null</code> ���� �� ���� ������� ����, ����������� ����� <code>data</code>.
     */
    TreeNode findParent(Object data);
    /**
     * ����� �������� ����� ������� <code>TreeNode</code> ����� ������� (������) ���� � �������� �������� <code>data</code>.<br/>
     * ������ ���� ����� ���� ����������: ���� ��������� �������� ���� �� ����� ��������� ������� <code>data</code>, 
     *  �� ������ ����� ����� ����� ��������� ����, � ��� �����.<br/>
     * ������� <code>data</code> ������ ������������ � ������� {@link Object#equals(Object)}, � ���� <code>data == null</code>,
     *  ����� ������ ������������ �������� ����, � �������� <code>getData() == null</code>).
     * @param data ������ ������; ����� ���� ����� <code>null</code>
     * @return ��������� ����. ��� <code>null</code> ���� �� ���� ������� ����, ����������� ����� <code>data</code>.
     */
    TreeNode findChild(Object data);
}
