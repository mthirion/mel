<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="1.0">
    <xsl:output method="text" encoding="ISO-8859-1" />
    <xsl:variable name="newline" select="'&#xA;'"/>
    <xsl:template match="Fruit">
        <xsl:for-each select="Customer">
            <xsl:value-of select="preceding-sibling::FruitId" />
            <xsl:text>,</xsl:text>
            <xsl:value-of select="NumberEaten" />
            <xsl:text>,</xsl:text>
            <xsl:value-of select="Weight" />
            <xsl:value-of select="$newline" />
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>