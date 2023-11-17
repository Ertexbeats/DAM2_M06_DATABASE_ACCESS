<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" />
<xsl:template match="/company/department">
<table border="1">
  <tr>
	  <th>Name</th>
	  <th>Job</th>
	  <th>Salary</th>
  </tr>
</table>
</xsl:template>
</xsl:stylesheet>