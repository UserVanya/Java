package ru.skillbench.tasks.text;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ���� ������ - ����������� � ����������� java.util.Map, 
 *   � ����� � API ��� ���������� � ����������� ����������������� (generic) �������.<br/>
 * �������������: ����������� � �������������� ���������� ��������� � Java
 *  ��� ���� �� � ������� API ��� ������ �� ��������.<br/>
 * <p/>
 * �������<br/>
 * ���������� ���������� ��������� ���� � ������ ��� ����� �������� ��������.<br/>
 * <br/>
 * ����������<br/>
 * ������ ��������� ����� ������������������ �������� ������ �� ����� 1�� �������, ���������� 
 *  �� ������ ������������ ����������� ��������, �������� ��������� � ��������� �����.<br/>
 * ����������:<br/>
 * - ���������� ���������� ��������� ���� � ������ ��� ����� �������� ��������
 *   (����� '���������' � '���������' ��������� ����� � ��� �� ������!),<br/>
 * - ����������� ���������� �������� � ������� �������� ����� ��������� �����.<br/>
 * �������������: <br/>
 * - ����������� ���������� �������� � {@link PrintStream},<br/>
 * - ��������� �� ������������ �����, ����������� ������ ������� ������ &lt;...&gt;.<br/>
 * <p/>
 * ����������:<br/>
 * - � HTML &gt; - ��� > (������), &lt; - ��� < (������), � ����������� ������� � ����� "��������" 
 *  ����, ����� ��� ��������� ������������ � HTML, ������� �� ��� ������������ ����� javadoc).<br/>
 *  �� ����, ��������������� ����� ������� ������: "������ ������� ������ <...>".<br/>
 * - ������ ������ �� ������� ������� �� ���� ����� ���������� (.,;:-), ������ ��� ������� 
 *   �� ����� ������ (����� ����� ������������� ��������, �� ����������� ���� ������).<br/>
 * - ��� ������������ ����� ����� ������� �����, ��������, �� http://www.gnu.org/licenses/lgpl-3.0.txt<br/>
 * 
 * @author Andrey Shevtsov
 */
public interface WordCounter
{
	/**
	 * ��������� ����� ��� �������
	 * @param text ����� ��� �������
	 */
	void setText(String text);
	
	/**
	 * @return �����, ���������� ��� ������� ��� ��������� ������ ������
	 * {@link #setText(java.lang.String) setText}, ��� <code>null</code>,
	 * ���� ��������� ����� ��� �� ��������� ��� ��������� ��� ���������
	 * � ���������� <code>null</code>
	 */
	String getText();
	
	/**
	 * ���������� {@link Map}&lt;{@link String}, {@link Long}&gt;, �������������� ������� 
	 *   ����� (������ �� ����� 1 �������) ���������� ��� ��������� � ������������� �����.<br/>
	 * ��� ������������ ����� ������ ���� ��������� � ������� ��������.<br/>
	 * ������������� �����������, ���� �� ������������ ��������� �����, ������������ � &lt;
	 *  � ��������������� �� &gt; (�� ����, ������������� � ������� �������).<br/>
	 * @return ��������� �������� ��������� ��������� ����
	 * @throws IllegalStateException ���� �� ����� ����� ��� �������
	 *   (���� ����� {@link #setText(String)} ��� �� ���������
	 *   ��� ��������� ��� ��������� � ���������� <code>null</code>)
	 */
	Map<String, Long> getWordCounts();
	
	/**
	 * ���������� ������ �� {@link Entry Map.Entry}&lt;{@link String}, {@link Long}&gt;,
	 * �������������� ������� ����� ���������� ��� ��������� � ������������� �����
	 * � ������������� � ������ �������� ���������� ��������� �����.<br/>
	 * ����� � ���������� ����������� ��������� ��������������� � ���������� ������� (��� ����� ��������!).<br/>
	 * ��� ������������ ����� ������ ���� ��������� � ������� ��������.<br/>
	 * 
	 * ����������: ��� ���������� ������������� ������������ {@link #sort(Map, Comparator)}
	 * @return ������������� ��������� �������� ��������� ��������� ����
	 * @throws IllegalStateException ���� �� ����� ����� ��� �������
	 *   (���� ����� {@link #setText(String)} ��� �� ���������
	 *   ��� ��������� ��� ��������� � ���������� <code>null</code>)
	 */
	List<Map.Entry<String, Long>> getWordCountsSorted();
	
	/**
	 * ������������� ���������� <code>map</code> (��� ����� � ���������� �� ���������) 
	 *  � ������������ � <code>comparator</code>.<br/>
	 * <br/>
	 * ����������:���� ����� �������� ������ �� ������ �����������, �� �� � ������ ������� {@link WordCounter}.
	 * @param map ��������, ��������������� ��������� �������� ����� ����
	 * @return ���������� <code>map</code> � ���� ������, �������������� � ������������ � <code>comparator</code>
	 */
	<K extends Comparable<K>, V extends Comparable<V>> List<Map.Entry<K,V>> sort(Map<K,V> map, Comparator<Map.Entry<K,V>> comparator);
	
	/**
	 * ������������� <code>entryList</code> (��� ����� � ���������� �� ���������) 
	 *  � ����� ������ <code>ps</code>.<br/>
	 * ������ ������ ���������:
	 * <ul>
	 *	<li>������ ����� ������ � ����������� ��������� ��������� �� ��������� ������</li>
	 *	<li>�� ������ ������ ����� � ���������� ��������� ��������� �����(!) ��������,
	 * ������� ������ �������� �� ������ ���� �� ������</li>
	 * </ul>
	 * ��� ��������� ����� ������ ���� ��������� � ������� ��������.<br/>
	 * <br/>
	 * ����������: ���� ����� �������� ������ �� ������ �����������, �� �� � ������ ������� {@link WordCounter}.
	 * @param entryList ������ ��� - ��������, ��������� �������� ����� ����
	 * @param ps ����� ������ - ��������, System.out.
	 */
	<K,V> void print(List<Map.Entry<K, V>> entryList, PrintStream ps);
}
