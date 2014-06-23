package org.apache.pdfboxandroid.pdmodel.common;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.pdfboxandroid.cos.COSDictionary;
import org.apache.pdfboxandroid.cos.COSName;

public class COSDictionaryMap<K, V> implements Map<K, V> {

	private COSDictionary map;
    private Map<K,V> actuals;

    /**
     * Constructor for this map.
     *
     * @param actualsMap The map with standard java objects as values.
     * @param dicMap The map with COSBase objects as values.
     */
    public COSDictionaryMap( Map<K,V> actualsMap, COSDictionary dicMap )
    {
        actuals = actualsMap;
        map = dicMap;
    }


    /**
     * {@inheritDoc}
     */
    public int size()
    {
        return map.size();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * {@inheritDoc}
     */
    public boolean containsKey(Object key)
    {
        return map.keySet().contains( key );
    }

    /**
     * {@inheritDoc}
     */
    public boolean containsValue(Object value)
    {
        return actuals.containsValue( value );
    }

    /**
     * {@inheritDoc}
     */
    public V get(Object key)
    {
        return actuals.get( key );
    }

    /**
     * {@inheritDoc}
     */
    public V put(K key, V value)
    {
        COSObjectable object = (COSObjectable)value;

        map.setItem( COSName.getPDFName( (String)key ), object.getCOSObject() );
        return actuals.put( key, value );
    }

    /**
     * {@inheritDoc}
     */
    public V remove(Object key)
    {
        map.removeItem( COSName.getPDFName( (String)key ) );
        return actuals.remove( key );
    }

    /**
     * {@inheritDoc}
     */
    public void putAll(Map<? extends K, ? extends V> t)
    {
        throw new RuntimeException( "Not yet implemented" );
    }

    /**
     * {@inheritDoc}
     */
    public void clear()
    {
        map.clear();
        actuals.clear();
    }

    /**
     * {@inheritDoc}
     */
    public Set<K> keySet()
    {
        return actuals.keySet();
    }

    /**
     * {@inheritDoc}
     */
    public Collection<V> values()
    {
        return actuals.values();
    }

    /**
     * {@inheritDoc}
     */
    public Set<Map.Entry<K, V>> entrySet()
    {
        return Collections.unmodifiableSet(actuals.entrySet());
    }

//    /**
//     * {@inheritDoc}
//     */
//    public boolean equals(Object o)
//    {
//        boolean retval = false;
//        if( o instanceof COSDictionaryMap )
//        {
//            COSDictionaryMap<K,V> other = (COSDictionaryMap)o;
//            retval = other.map.equals( this.map );
//        }
//        return retval;
//    }

//    /**
//     * {@inheritDoc}
//     */
//    public String toString()
//    {
//        return actuals.toString();
//    }

//    /**
//     * {@inheritDoc}
//     */
//    public int hashCode()
//    {
//        return map.hashCode();
//    }

//    /**
//     * This will take a map&lt;java.lang.String,org.apache.pdfbox.pdmodel.COSObjectable&gt;
//     * and convert it into a COSDictionary&lt;COSName,COSBase&gt;.
//     *
//     * @param someMap A map containing COSObjectables
//     *
//     * @return A proper COSDictionary
//     */
//    public static COSDictionary convert( Map<?,?> someMap )
//    {
//        Iterator<?> iter = someMap.keySet().iterator();
//        COSDictionary dic = new COSDictionary();
//        while( iter.hasNext() )
//        {
//            String name = (String)iter.next();
//            COSObjectable object = (COSObjectable)someMap.get( name );
//            dic.setItem( COSName.getPDFName( name ), object.getCOSObject() );
//        }
//        return dic;
//    }

//    /**
//     * This will take a COS dictionary and convert it into COSDictionaryMap.  All cos
//     * objects will be converted to their primitive form.
//     *
//     * @param map The COS mappings.
//     * @return A standard java map.
//     * @throws IOException If there is an error during the conversion.
//     */
//    public static COSDictionaryMap<String, Object> convertBasicTypesToMap( COSDictionary map ) throws IOException
//    {
//        COSDictionaryMap<String, Object> retval = null;
//        if( map != null )
//        {
//            Map<String, Object> actualMap = new HashMap<String, Object>();
//            for( COSName key : map.keySet() )
//            {
//                COSBase cosObj = map.getDictionaryObject( key );
//                Object actualObject = null;
//                if( cosObj instanceof COSString )
//                {
//                    actualObject = ((COSString)cosObj).getString();
//                }
//                else if( cosObj instanceof COSInteger )
//                {
//                    actualObject = new Integer( ((COSInteger)cosObj).intValue() );
//                }
//                else if( cosObj instanceof COSName )
//                {
//                    actualObject = ((COSName)cosObj).getName();
//                }
//                else if( cosObj instanceof COSFloat )
//                {
//                    actualObject = new Float( ((COSFloat)cosObj).floatValue() );
//                }
//                else if( cosObj instanceof COSBoolean )
//                {
//                    actualObject = ((COSBoolean)cosObj).getValue() ? Boolean.TRUE : Boolean.FALSE;
//                }
//                else
//                {
//                    throw new IOException( "Error:unknown type of object to convert:" + cosObj );
//                }
//                actualMap.put( key.getName(), actualObject );
//            }
//            retval = new COSDictionaryMap<String, Object>( actualMap, map );
//        }
//
//        return retval;
//    }
    
    /**
     * This will take a map&lt;java.lang.String,org.apache.pdfbox.pdmodel.COSObjectable&gt;
     * and convert it into a COSDictionary&lt;COSName,COSBase&gt;.
     *
     * @param someMap A map containing COSObjectables
     *
     * @return A proper COSDictionary
     */
    public static COSDictionary convert( Map<?,?> someMap )
    {
        Iterator<?> iter = someMap.keySet().iterator();
        COSDictionary dic = new COSDictionary();
        while( iter.hasNext() )
        {
            String name = (String)iter.next();
            COSObjectable object = (COSObjectable)someMap.get( name );
            dic.setItem( COSName.getPDFName( name ), object.getCOSObject() );
        }
        return dic;
    }
}