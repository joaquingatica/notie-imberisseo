<h1>ImladrisCalendar.java</h1>
<strong>Version 1.1 beta</strong>

<h2>Files</h2>
<pre>src/ImladrisCalendar.java</pre> - The class that manages de Calendar itself
<pre>src/NotieImberisseo.java</pre> - A demo main() to test the calendar.

<h2>Usage</h2>

<h3>Constructors</h3>

When a new instance is created, the specified date is automatically calculated. This date can be specified using the diferent constructors. 

<pre><code>
ImladrisCalendar(); /* Uses today's date */
</code></pre>
<pre><code>
ImladrisCalendar(GregorianCalendar gregorian); /* Uses the date of the given GregorianCalendar object */
</code></pre>
<pre><code>
ImladrisCalendar(int year, int month, int dayOfMonth); /* Uses the Gregorian date given. Valid values: year 1-2299 | month 1-12 | day 1-[# of days of month 'month'] */
</code></pre>
<pre><code>
ImladrisCalendar(String yen, int loa, int period); /* Uses Imladris date given. Only for Yestare and Mettare. Valid values: yen roman # "I"-"XVI" | loa 1-144 | period 1 OR 9 */
</code></pre>
<pre><code>
ImladrisCalendar(String yen, int loa, int period, int day); /* Uses Imladris date given. Valid values: yen roman # "I"-"XVI" | loa 1-144 | period 1-9 | day 1-[# of days of period 'period'] */
</code></pre>

NOTE: loar 140-144 of Yén XVI are not fully tested, and may work wrongly.
