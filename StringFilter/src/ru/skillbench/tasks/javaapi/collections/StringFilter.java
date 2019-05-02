package ru.skillbench.tasks.javaapi.collections;

import java.util.Collection;
import java.util.Iterator;

/**
 * ���� ������:<br/>
 * - ��������� �������� ���������� �����-��������� � �������������� ��;<br/>
 * - ���������, ��� � ���������� ����� ��������� �������� null.<br/>
 * - ��������� �������� ���������� ���� ��� ���������� ������� ����������;<br/>
 * - ��������� ������������ ����� ��� ������ ������ String (����� ��������� � regex);<br/>
 * - ��������, ��� �� ��������� ����� ����� ����� ������� ���������� ��������� (regex) :), 
 *   ��� ��� ��� ��� ������ "������������� �� ������ �������" �������� ������������
 *   ���� � ������ ������ ������������ �������.<br/>
 * <p/>
 * �������<br/>
 * ����������� ����� �������, ������� ������ ����� ����� (String), ����������� � ������� ��������
 *  (� ������ �� ����� ���� ���� ���������� �����, �� ����� ���� null).<br/>
 * ����������� ���������� ����� ����� ������, �.�. ���������� ��������� ({@link Iterator}) 
 *   � ���� ��������, ������� ������������� ���������� ���������.<br/>
 * <p/>
 * ����������<br/>
 * ����� ����� ������ ��������� � ������� ������������ ������ �� Java Collections Framework.
 * ���������� ������ ������������� �������� {@link #add(String)} � {@link #remove(String)}.<br/>
 * �������� ���������� �����:
 * <ul>
 *	<li>������, ���������� �������� ������������������ ��������</li>
 *	<li>������, ������������ � �������� ������������������ ��������</li>
 *	<li>������, ��������������� ��������� ��������� �������, ����������� ������� # 
 *      � �������� digit placeholders (# - ����� ��� ����� ����� �� 0 �� 9)</li>
 *	<li>������, ��������������� ��������� ������� ������, ����������� ������� * � �������� wildcards
 *      (�� ����� * � ������ ����� ���� ���� ��� ������ ����� ��������)</li>
 * </ul>
 * ���������� ����������� ��� 4 ������ ���, ����� � ��� ��� ����������� ����� �������������� ����<br/>
 * <p/>
 * ���������� ��� �������� ���<br/>
 * ����� ������� ������ ����������� ��������� ���������� - ������� ��������� ��������� Filter, 
 * � ������������ ����� �������� ������������ ��������: ������������� �� �������� ������ (�� ������)
 * �������� ����������. ������ �� 4� ������� ������ ��������� ���� ���������� Filter (��������� �����).
 * ��� ��������� ������ 4� ������� ��������� (�������� ���������, ���� �� ������� ������ � ��.) 
 * � ������ ���� ������������ ���������� ������, ������������ Filter � ������������� Iterator.<br/>
 * 
 * @author Alexey Evdokimov
 */
public interface StringFilter {
	/**
	 * ��������� ������ s � �����, ������� �� � ������� ��������.
	 * ���� ������ s ��� ���� � ������, ������ �� ������.
	 * @param s ����� ���� null
	 */
	void add(String s);
	/**
	 * ������� ������ s �� ������ (�������������� ������� �� � ������� ��������).
	 * @param s ����� ���� null
	 * @return true ���� ������ ���� �������, false ���� ������ ������������� � ������.
	 */
	boolean remove(String s);
	/**
	 * ������� ����� - ������� �� ���� ��� ������
	 */
	void removeAll();
	/**
	 * ���������� ����� (���������), � ������� �������� ������.
	 * � ������ �� ����� ���� ���� ���������� �����, ������ ����� ���� null.
	 */
	Collection<String> getCollection();
	
	/**
	 * ���� � ���������� ��� ������, ���������� ��������� ������������������ ��������.<br/>
	 * ���� <code>chars</code> - ������ ������ ��� <code>null</code>,
	 * �� ��������� �������� ��� ������ ������� ������.<br/>
	 * @param chars �������, �������� � ������� ������
	 *   (��� �������, ���������� �������, - � ������ ��������)
	 * @return ������, ���������� ��������� ������������������ ��������
	 */
	Iterator<String> getStringsContaining(String chars);
	/**
	 * ���� � ���������� ������, ������������ � ��������� ������������������ ��������,
	 *  (��� ����� ��������). <br/>
	 * ���� <code>begin</code> - ������ ������ ��� <code>null</code>,
	 * �� ��������� �������� ��� ������ ������� ������.<br/>
	 * @param begin ������ ������� ������� ����� 
	 *   (��� ��������� �� �������� ������ ������� ����� �������� � ������� ��������)
	 * @return ������, ������������ � ��������� ������������������ ��������
	 */
	Iterator<String> getStringsStartingWith(String begin);
	/**
	 * ���� � ���������� ��� ������, �������������� ����� ����� � �������� �������.<br/>
	 * ������ ����� ��������� ������ # (����� ��� ����� ����� �� 0 �� 9) � ����� �������.
	 * ������� ��������: "(###)###-####" (�������), "# ###" (����� ����� �� 1000 �� 9999), 
	 *  "-#.##" (������������� �����, ������� -10, � ����� ����� ������� ����� ���������� �����).<br/>
	 * ���������� �����������: � ������, ��������������� �������, ������ ���� ����� ������� ��������, 
	 *  ������� � ������� (� ������� �� ������������ ��������� ��������� �������, 
	 *  ��� ��������� ����� �� ����� # �� �������� �������������).<br/>
	 * ����������: � ������ ���������� ������ �� �������������� ������������� ���������� ��������� 
	 *  ��� ������-���� ���������������� API (��� ���� ��������� �����).<br/>
	 * ���� <code>format</code> - ������ ������ ��� <code>null</code>,
	 * �� ��������� �������� ��� ������ ������� ������.<br/>
	 * @param format ������ �����
	 * @return ������, ��������������� ��������� ��������� �������
	 */
	Iterator<String> getStringsByNumberFormat(String format);
	/**
	 * ���� � ���������� ������, ��������������� ��������� ������� ������, ����������� ������� * 
	 * � �������� wildcards (�� ����� * � ������ ����� ���� ���� ��� ������ ����� ��������).<br/>
	 * <a href="http://en.wikipedia.org/wiki/Wildcard_character#File_and_directory_patterns">��� * wildcard</a>.<br/>
	 * ������� ��������, ������� ������������� ������ "distribute": "distr*", "*str*", "di*bute*".<br/>
	 * ���������: ���������� ������������ ����� ��� ������� * � ������� (�� ����� ���� � ������ ����).<br/>
	 * ����������: � ������ ���������� ������ �� �������������� ������������� ���������� ��������� 
	 *  � ������-���� ���������������� API (��� ���� ��������� �����), ���� - ��������� ������ String.<br/>
	 * ���� <code>pattern</code> - ������ ������ ��� <code>null</code>,
	 * �� ��������� �������� ��� ������ ������� ������.<br/>
	 * @param pattern ������ ������ (��� ����� � ��� - � ������ ��������)
	 * @return ������, ��������������� ��������� ������� ������
	 */
	Iterator<String> getStringsByPattern(String pattern);
}