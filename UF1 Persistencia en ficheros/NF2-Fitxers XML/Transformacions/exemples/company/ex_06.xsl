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
	<xsl:for-each select="company/department/employee">
	<xsl:choose>
		<xsl:when test="salary &gt; 2700">
		<tr bgcolor="#66CCFF">
	      <td><xsl:value-of select="name"/></td>
	      <td><xsl:value-of select="job"/></td>
	      <td><xsl:value-of select="salary"/></td>
	    </tr>
		</xsl:when>
		<xsl:otherwise>
		<tr bgcolor="#00CC99">
	      <td><xsl:value-of select="name"/></td>
	      <td><xsl:value-of select="job"/></td>
	      <td><xsl:value-of select="salary"/></td>
	    </tr>
		</xsl:otherwise>
	</xsl:choose>
	</xsl:for-each>
</table>
</xsl:template>
</xsl:stylesheet>