# EmployeeManagementTree

Management Tree Algorithm

For this exercise, you need to write a small piece of logic for the HR lady of a company, who wants to have a quick and easy way to see how employees of her company are managed now.
Your routine will take a list of employees as input, and display the employee relationships in a required format.



Your main routine, displayManagementTree(List&lt;Employee&gt;), should output the following:

-&gt;Tom

-&gt;-&gt;Jerry

-&gt;-&gt;Mickey

-&gt;-&gt;-&gt;John

-&gt;-&gt;Sarah

Where:

* An employee has an ID, a name, and a manager ID

* The printed tree must start from a root manager. You can assume that a root manager
has a manager ID of zero (0). The tree must contain one and only one root manager. It is
your responsibility to ensure that is the case no matter what input you get.

* Each -&gt; represents one level of management. The deeper the management structure,
the more -&gt; before the employee name. The root manager is at the top level, hence
there is only one -&gt;, e.g., -&gt;Tom

* If an employee has a manager, then he/she should be displayed below their direct
manager.
