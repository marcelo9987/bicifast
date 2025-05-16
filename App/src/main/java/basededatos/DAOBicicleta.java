package basededatos;

import aplicacion.*;

import java.sql.Connection;
import java.util.List;

public class DAOBicicleta extends AbstractDAO {
    public DAOBicicleta(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }


    public List<Bicicleta> obtenerBicisPorEstacion(Estacion estacionUsada) {
        java.sql.PreparedStatement stmBicisLibres = null;
        java.sql.ResultSet         datosEntrada;
        List<Bicicleta>            resultado      = new java.util.ArrayList<>();
        Connection                 con            = this.getConexion();
        String consulta =
                "Select id, estado "
                        + "From bicicleta "
                        + "Where estacion = ? ";
        try {
            stmBicisLibres = con.prepareStatement(consulta);
            stmBicisLibres.setInt(1, estacionUsada.idEstacion());
            datosEntrada = stmBicisLibres.executeQuery();
            while (datosEntrada.next()) {
                resultado.add(new Bicicleta(datosEntrada.getInt("id"), estacionUsada, EstadoBicicleta.valueOf(datosEntrada.getString("estado"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("EXCEPCION_CONSULTA_BICICLETAS");
        } finally {
            try {
                if (stmBicisLibres != null) {
                    stmBicisLibres.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("IMPOSIBLE_CERRAR_CONEXION");
            }
        }

        return resultado;
    }

    public List<Bicicleta> obtenerBicisEnUsoPorEstacion(Estacion estacionUsada) {
        java.sql.PreparedStatement stmBicisLibres = null;
        java.sql.ResultSet         datosEntrada;
        List<Bicicleta>            resultado      = new java.util.ArrayList<>();
        Connection                 con            = this.getConexion();

        String consulta =
                "SELECT " +
                        "    b.id " +
                        "   , b.estado " +
                        "FROM " +
                        "    bicicleta b  LEFT JOIN estacion e ON " +
                        "        b.estacion = e.id  " +
                        "            AND " +
                        "        e.id = ? " +
                        "WHERE " +
                        "    b.id in (Select " +
                        "                 b.id " +
                        "                 FROM " +
                        "                     bicicleta b LEFT JOIN " +
                        "                     viaje v ON " +
                        "                         b.id = v.bicicleta " +
                        "                 WHERE " +
                        "                     v.hora_fin IS NULL)";
        try {
            stmBicisLibres = con.prepareStatement(consulta);
            stmBicisLibres.setInt(1, estacionUsada.idEstacion());
            datosEntrada = stmBicisLibres.executeQuery();
            while (datosEntrada.next()) {
                resultado.add(new Bicicleta(datosEntrada.getInt("id"), estacionUsada, EstadoBicicleta.valueOf(datosEntrada.getString("estado"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("EXCEPCION_CONSULTA_BICICLETAS");
        } finally {
            try {
                if (stmBicisLibres != null) {
                    stmBicisLibres.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("IMPOSIBLE_CERRAR_CONEXION");
            }
        }
        return resultado;
    }

    public void estacionarBicicleta(Bicicleta bicicleta, Estacion estacionSeleccionada) {
        java.sql.PreparedStatement stmBicisLibres = null;
        Connection                 con            = this.getConexion();
        String consulta =
                "UPDATE bicicleta " +
                        "SET estacion = ? " +
                        "WHERE id = ?";
        try {
            stmBicisLibres = con.prepareStatement(consulta);
            stmBicisLibres.setInt(1, estacionSeleccionada.idEstacion());
            stmBicisLibres.setInt(2, bicicleta.Id());
            stmBicisLibres.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("EXCEPCION_CONSULTA_BICICLETAS");
        } finally {
            try {
                if (stmBicisLibres != null) {
                    stmBicisLibres.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("IMPOSIBLE_CERRAR_CONEXION");
            }
        }
    }

    public List<Integer> preguntaLasBicicletasPorEstacion() {
        // cuenta por orden de ubicacion cuantas bicicletas hay en cada estacion
        java.sql.PreparedStatement stmBicisLibres = null;
        java.sql.ResultSet         datosEntrada;
        List<Integer>              resultado      = new java.util.ArrayList<>();
        Connection                 con            = this.getConexion();
        String consulta =
/*                "SELECT " +
//                        "    e.id, " +
//                        "    count(b.id) as cuenta " +
//                        "FROM " +
//                        "    estacion e LEFT JOIN bicicleta b ON " +
//                        "        e.id = b.estacion " +
//                        " WHERE " +
//                        "   b.id in (Select " +
//                        "                 b.id " +
//                        "                 FROM " +
//                        "                     bicicleta b LEFT JOIN " +
//                        "                     viaje v ON " +
//                        "                         b.id = v.bicicleta " +
//                        "                 WHERE " +
//                        "                     v.hora_fin IS NULL) " +
//                        "GROUP BY e.id"
//                        + " ORDER BY e.ubicacion";
//        "SELECT" +
//                "    e.id," +
//                "    COUNT(CASE WHEN b.id IN ( " +
//                "        SELECT b2.id " +
//                "            FROM bicicleta b2 " +
//                "                     LEFT JOIN viaje v ON b2.id = v.bicicleta " +
//                "            WHERE v.hora_fin IS NULL " +
//                "    ) THEN 0 ELSE 1 END) as cuenta " +
//                "    FROM " +
//                "        estacion e " +
//                "            LEFT JOIN " +
//                "        bicicleta b ON e.id = b.estacion " +
//                "    GROUP BY" +
//                "        e.id" +
//                "    ORDER BY" +
               "        e.ubicacion";*/
                "SELECT"
                        + " e.id,"
                        + " COUNT(IF(NOT EXISTS (SELECT 1"
                        + "                         FROM viaje v"
                        + "                         WHERE v.bicicleta = b.id"
                        + "                           AND v.hora_fin IS NULL), 1, NULL)) AS cuenta"
                        + "            FROM"
                        + "                estacion e"
                        + "                LEFT JOIN bicicleta b ON e.id = b.estacion"
                        + "            GROUP BY"
                        + "                e.id"
                        + "            ORDER BY"
                        + "                 e.ubicacion";
        try {
            stmBicisLibres = con.prepareStatement(consulta);
            datosEntrada = stmBicisLibres.executeQuery();
            while (datosEntrada.next()) {
                resultado.add(datosEntrada.getInt("cuenta"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("EXCEPCION_CONSULTA_BICICLETAS");
        } finally {
            try {
                if (stmBicisLibres != null) {
                    stmBicisLibres.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("IMPOSIBLE_CERRAR_CONEXION");
            }
        }
        return resultado;

    }

    public boolean reservarBicicleta(Usuario usuarioLogado, Bicicleta bicicletaReservada) {
        java.sql.PreparedStatement stmBicisLibres = null;
        Connection                 con            = this.getConexion();
        String consulta =
                "INSERT INTO viaje " +
                        "(usuario, bicicleta, hora_inicio, origen) " +
                        "VALUES (?, ?, ?, ?)";
        try {
            stmBicisLibres = con.prepareStatement(consulta);
            stmBicisLibres.setInt(1, usuarioLogado.idUsuario());
            stmBicisLibres.setInt(2, bicicletaReservada.Id());
            stmBicisLibres.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
            stmBicisLibres.setInt(4, bicicletaReservada.estacion().idEstacion());
            stmBicisLibres.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("EXCEPCION_CONSULTA_BICICLETAS");
            return false;
        } finally {
            try {
                if (stmBicisLibres != null) {
                    stmBicisLibres.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("IMPOSIBLE_CERRAR_CONEXION");
                return false;
            }
        }
        return true;
    }
}
