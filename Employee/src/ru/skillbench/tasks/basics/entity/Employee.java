package ru.skillbench.tasks.basics.entity;

/**
 * ���� ������ - ��������� ��������� ����� ��������-���������������� ���������������� � Java,
 * �������� ��������� ������, ���������� ������� get/set, ��������� � ����� � ������� ��������.
 *
 * �������:
 * ����������� �����, �������������� ����� �������� ���������� �������� (Employee).
 *
 * ����������:
 * 1) ��������� ������, ������������ ������ ���������, ������ ��������������� ������ ����������.
 * 2) ���������� ����������� ������ get/set ��� ����� (� �������), ������� �����, �������� ����������,
 *  � ����� ��� ����������������� ��������� � �������� (������ ������������) ���������.
 * ������ (����) ������ ���� ��������� ��� private-���������� ������.
 * ������ get** / set** ������ ����������� � ����� ������.
 * 3) �� ��������� (� ������ ��� ���������� ����������) �������� ������ ���� ����� 1000.
 *
 * ����� ���������� (�� ���� �������� ����� ��� �� �������������� ��������):
 * ��� ��� ������ ��������� ���������� ������� ����������:
 * public class EmployeeImpl implements Employee {  }
 * ���� � ���� ���� ������������, �� ����� ��� ������ ���� ����������� ��� ����������:
 * public EmployeeImpl() {  }
 *
 * ����������:
 * ������ ����� ������ ��� ����� ��������� � ��������� �������������� �������� (Exceptions).
 *
 * @author Alexey Evdokimov
 * @author Alexander Kharichkin
 */
public interface Employee {
    /**
     * @return �������� ���������� �� ��������� ������.
     */
    int getSalary();

    /**
     * ����������� �������� ���������� �� �������� ��������
     * @param value ��������, �� ������� ����� ���������
     */
    public void increaseSalary(int value);

    /**
     * @return ��� ����������
     */
    public String getFirstName();

    /**
     * ������������� ��� ����������
     * @param firstName ����� ���
     */
    public void setFirstName(String firstName);

    /**
     * @return ������� ����������
     */
    public String getLastName();

    /**
     * ������������� ������� ����������
     * @param lastName ����� �������
     */
    public void setLastName(String lastName);

    /**
     * @return ��� � ����� ������� ����������, ����������� �������� " " (������)
     */
    public String getFullName();

    /**
     * ������������� ��������� ����������.
     * @param manager ���������, ���������� ���������� ������� ����������.
     * �� ������� ������������, ��� �������� �������� ����������� ������ EmployeeImpl.
     */
    public void setManager(Employee manager);

    /**
     * @return ��� � ������� ���������, ����������� �������� " " (������).
     * ���� �������� �� �����, ���������� ������ "No manager".
     */
    public String getManagerName();

    /**
     * ���������� ��������� �������� ������, �.�. ������� �������� �����������,
     *   � ������� ������ ������ ���������.
     * ���� ��� ������ ����������� ��� �� ������ ���������, ���������� ������� ����������.
     * ���������: ��������� ��������, ������������� ������� {@link #setManager(Employee)},
     *   ����� ���� ���������� ������� ������, ��� ������ ���-��������� ������ ����������
     *   � ����� ������ EmployeeImpl. ����� ����, ��������� � ���������� Employee �� ���������
     *   ������ getManager(), ����� ���-��������� ���������� ������������ � ���� �����.
     *   ������ ����� ����� ������������ �������� (� ��� "����� ��������-��������������").
     */
    public Employee getTopManager();
}