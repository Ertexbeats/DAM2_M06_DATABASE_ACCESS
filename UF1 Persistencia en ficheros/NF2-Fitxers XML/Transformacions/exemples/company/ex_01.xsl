<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" />
<xsl:template match="/">
<table border="1">
	<tr>
		<th>Name</th>
		<th>Job</th>
		<th>Salary</th>
	</tr>
	<tr>
		<td><xsl:value-of select="/company/department/employee/name"/></td>
		<td><xsl:value-of select="/company/department/employee/job"/></td>
		<td><xsl:value-of select="/company/department/employee/salary"/></td>
	</tr>
</table>
</xsl:template>
</xsl:stylesheet>