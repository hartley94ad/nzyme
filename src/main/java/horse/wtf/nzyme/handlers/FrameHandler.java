/*
 *  This file is part of Nzyme.
 *
 *  Nzyme is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Nzyme is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Nzyme.  If not, see <http://www.gnu.org/licenses/>.
 */

package horse.wtf.nzyme.handlers;

import horse.wtf.nzyme.Nzyme;
import horse.wtf.nzyme.dot11.Dot11MetaInformation;
import org.pcap4j.packet.IllegalRawDataException;

public abstract class FrameHandler {

    protected final Nzyme nzyme;

    protected FrameHandler(Nzyme nzyme) {
        this.nzyme = nzyme;
    }

    protected void tick() {
        nzyme.getStatistics().tickType(getName());

    }

    public void malformed(Dot11MetaInformation meta) {
        nzyme.getStatistics().tickMalformedCountAndNotify(nzyme, meta);
    }

    public abstract void handle(byte[] payload, byte[] header, Dot11MetaInformation meta) throws IllegalRawDataException;
    public abstract String getName();

}
